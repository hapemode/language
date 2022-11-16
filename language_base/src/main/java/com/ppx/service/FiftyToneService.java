package com.ppx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ppx.common.base.BaseResult;
import com.ppx.entity.BO.FiftyToneBO;
import com.ppx.entity.FiftyTone;
import com.ppx.request.FiftyToneReq;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hape
 * @since 2022-11-15 15:20:06
 */
public interface FiftyToneService extends IService<FiftyTone> {

    void importData(MultipartFile file);

    void export(HttpServletResponse response);

    BaseResult<List<FiftyToneBO>> getData(int size);

    BaseResult compare(FiftyToneReq req);
}
