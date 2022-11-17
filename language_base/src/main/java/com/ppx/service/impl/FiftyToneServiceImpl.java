package com.ppx.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.MapUtils;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ppx.common.base.BaseResult;
import com.ppx.common.listener.UploadDataListener;
import com.ppx.entity.BO.FiftyToneBO;
import com.ppx.entity.FiftyTone;
import com.ppx.mapper.FiftyToneMapper;
import com.ppx.request.FiftyToneReq;
import com.ppx.service.FiftyToneService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        try {
            // TODO: 2022/11/17 待验证
            EasyExcel.read(file.getInputStream(), FiftyTone.class, new UploadDataListener(this)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void export(HttpServletResponse response) {
        List<FiftyTone> list = query().list();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        try {
            String fileName = URLEncoder.encode("日语五十音", "UTF-8")
                    .replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), FiftyToneBO.class)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .sheet("模板").doWrite(list);
        } catch (Exception e) {
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = MapUtils.newHashMap();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            try {
                response.getWriter().println(JSON.toJSONString(map));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

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
