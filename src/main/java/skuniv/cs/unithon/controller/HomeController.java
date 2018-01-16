package skuniv.cs.unithon.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/photo", method=RequestMethod.POST)
    public void uploadImageCtlr(ModelMap model,
                                  HttpServletRequest request,
                                  MultipartRequest file
                                  ) throws UnsupportedEncodingException{
    	
		request.setCharacterEncoding("UTF-8");
		
		MultipartFile fn=file.getFile("photo");
		
    	System.out.println("upload");
        String rootPath = "C:\\Users\\gunyoungkim";
        System.out.println(rootPath);
        File dir = new File(rootPath + File.separator + "img");
        System.out.println(dir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File serverFile = new File(dir.getAbsolutePath() + File.separator + fn.getOriginalFilename());
        System.out.println(serverFile);
        String latestUploadPhoto = fn.getOriginalFilename();
        System.out.println(latestUploadPhoto);
        //write uploaded image to disk
        try {
            try (InputStream is = fn.getInputStream();
                 BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                int i;
                while ((i = is.read()) != -1) {
                    stream.write(i);
                }
                stream.flush();
            }
        } catch (IOException e) {
            System.out.println("error : " + e.getMessage());
        }

        //send photo name to jsp
        model.addAttribute("photo", latestUploadPhoto);
    }
	
	
   }
	

