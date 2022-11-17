package com.ppx.common.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.ppx.entity.FiftyTone;
import com.ppx.service.impl.FiftyToneServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hape
 * @Date: 2022/11/17 13:47
 */
@Slf4j
public class UploadDataListener implements ReadListener<FiftyTone> {

    private static final int count = 100;
    private List<FiftyTone> list = new ArrayList<>();

    private FiftyToneServiceImpl fiftyToneService;

    public UploadDataListener(FiftyToneServiceImpl fiftyToneService) {
        this.fiftyToneService = fiftyToneService;
    }

    @Override
    public void invoke(FiftyTone fiftyTone, AnalysisContext analysisContext) {
        list.add(fiftyTone);
        if (list.size() >= count) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("导入完毕");
    }

    private void saveData() {
        fiftyToneService.saveBatch(list);
    }

}
