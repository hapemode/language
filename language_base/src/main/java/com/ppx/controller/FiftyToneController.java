package com.ppx.controller;

import com.ppx.common.base.BaseResult;
import com.ppx.entity.BO.FiftyToneBO;
import com.ppx.request.FiftyToneReq;
import com.ppx.service.FiftyToneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hape
 * @since 2022-11-15 15:20:06
 */
@RestController
@RequestMapping("/fiftyTone")
public class FiftyToneController {

    @Autowired
    private FiftyToneService fiftyToneService;

    /**
     * 导入样本
     *
     * @param file
     */
    @PostMapping("/import")
    public void importData(MultipartFile file) {
        fiftyToneService.importData(file);
    }

    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        fiftyToneService.export(response);
    }

    @PostMapping("/getData")
    public BaseResult<List<FiftyToneBO>> getData(@RequestBody FiftyToneReq req) {
        return fiftyToneService.getData(req.getSize());
    }

    @PostMapping("/compare")
    public BaseResult compare(@RequestBody FiftyToneReq req) {
        return fiftyToneService.compare(req);
    }
}
