package com.springapp.mvc.api;

import com.springapp.mvc.model.BookInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kmchu on 16/5/4.
 */
@Controller
public class BookInfoController {

    @RequestMapping(value ="/book/{id}")
    @ResponseBody
    public ResponseEntity<BookInfo> getBookInfo(@PathVariable("id") int id){
        BookInfo info = new BookInfo();
        info.setBookId(id);
        info.setAuthor("author");
        info.setBookName("Nobody knows why");
        info.setPublicTime("In the later future");
        ResponseEntity<BookInfo> entity = new ResponseEntity<BookInfo>(info,HttpStatus.OK);
        return entity;
    }
}
