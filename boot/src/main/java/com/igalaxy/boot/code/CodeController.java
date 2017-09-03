package com.igalaxy.boot.code;

import com.igalaxy.boot.domain.CodeDomain;
import com.igalaxy.boot.mapper.CodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by fuguolei on 2017/2/6.
 */
@Controller
@RequestMapping("/admin/code")
public class CodeController {

    @Autowired
    CodeMapper codeMapper;

    @RequestMapping("create")
    public String code(String name) {
        List<CodeDomain> list = codeMapper.queryByName(name);

        return null;
    }
}
