package com.myparty.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.myparty.controller.middleware.NotificationMiddleware;
import com.myparty.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.Data;

@Data
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

	@Autowired
	private NotificationMiddleware notificationMiddleware;

	private Map<String, SseEmitter> sses = new ConcurrentHashMap<>();
	
	@GetMapping(value = "/sse/{username}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public SseEmitter streamFlux(@PathVariable("username") String username) {
		SseEmitter emitter = new SseEmitter(60 * 60 * 1000l);
 
		sses.put(username, emitter);
		emitter.onError(t -> {
			sses.remove(username);
			System.err.println(username + " finalizou com erro: " + t.getMessage());
		});
		
		emitter.onCompletion(() -> {
			sses.remove(username);
			System.out.println(username + " finalizou com completation.");
		});
		
		emitter.onTimeout(() -> {
			sses.remove(username);
			System.err.println(username + " finalizou com timeout.");
		});
		
		return emitter;
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<NotificationDTO>> getNotificationsByUserId(@PathVariable("id") Long id) {
		return ResponseEntity.ok(notificationMiddleware.getNotificationsByUserId(id));
	}

}