package com.ppx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ppx.common.base.BaseResult;
import com.ppx.entity.BO.FiftyToneBO;
import com.ppx.entity.FiftyTone;
import com.ppx.mapper.FiftyToneMapper;
import com.ppx.request.FiftyToneReq;
import com.ppx.service.FiftyToneService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hape
 * @since 2022-11-15 15:20:06
 */
@Service
public class FiftyToneServiceImpl extends ServiceImpl<FiftyToneMapper, FiftyTone> implements FiftyToneService {

    @Override
    public void importData(MultipartFile file) {

    }

    @Override
    public void export(HttpServletResponse response) {

    }

    @Override
    public BaseResult<List<FiftyToneBO>> getData(int size) {
        List<Integer> list = new ArrayList<>();
        Integer count = query().list().size();
        for (int i = 0; i < size; i++) {
            list.add(new Random().nextInt(count) + 1);
        }
        List<FiftyTone> respList = query().in("id", list).list();
        BaseResult result = BaseResult.getSuccessResult();
        result.setData(respList);
        return result;
    }

    @Override
    public BaseResult compare(FiftyToneReq req) {
        Integer count = query().eq(!StringUtils.isEmpty(req.getHiragana()), "hiragana", req.getHiragana())
                .eq(!StringUtils.isEmpty(req.getHiraganaWrite()), "hiragana_write", req.getHiraganaWrite())
                .eq(!StringUtils.isEmpty(req.getKatakana()), "katakana", req.getKatakana())
                .eq(!StringUtils.isEmpty(req.getKatakanaWrite()), "katakana_write", req.getKatakanaWrite())
                .list().size();
        if (count == 1) {
            return BaseResult.getSuccessResult();
        } else {
            return BaseResult.getFailResult();
        }
    }
}
