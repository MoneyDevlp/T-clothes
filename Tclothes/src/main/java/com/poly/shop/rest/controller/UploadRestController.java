package com.poly.shop.rest.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.poly.shop.service.UploadService;

@CrossOrigin("*")
@RestController
public class UploadRestController {
	
	@Autowired
	UploadService uploadService;
	
	@PostMapping("/rest/files/{folder}")
	public JsonNode upload(@PathParam("files") MultipartFile files, @PathVariable("folder") String folder) {
		File saveFile = uploadService.save(files, folder);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("name", saveFile.getName());
		node.put("size", saveFile.length());
		return node;
	}
	
	@GetMapping("/rest/files/{folder}/{file}")
	public byte[] dowload(@PathVariable("folder") String folder, @PathVariable("file") String file,
			HttpServletResponse response) {
		response.setHeader("Content-Disposition", "attachment;filename=" + file);
		return uploadService.read(folder, file);
	}
	
	@GetMapping("/rest/files/{folder}")
	public List<String> list(@PathVariable("folder") String folder){
		return uploadService.list(folder);
	}
	
//	@PostMapping("/rest/files/{folder}")
//	public List<String> upload(@PathVariable("folder") String folder,@PathParam("files") MultipartFile[] files){
//		return uploadService.save(folder, files);
//	}
}
