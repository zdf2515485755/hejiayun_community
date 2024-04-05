package com.zdf.internalcommon.util;

import com.alibaba.excel.EasyExcelFactory;
import com.zdf.internalcommon.constant.ExcelConstant;
import com.zdf.internalcommon.entity.ExcelExportEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 *@Description 类功能简要描述
 *@Author mrzhang
 *@Date 2024/4/6 01:34
 */
public class ExcelUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private ExcelUtil(){}

    public static void downloadFile(HttpServletResponse httpServletResponse, String excelName, String sheetName, List<ExcelExportEntity> excelExportEntities){
        httpServletResponse.setContentType(ExcelConstant.CONTENT_TYPE);
        httpServletResponse.setCharacterEncoding(ExcelConstant.CHARACTER_ENCODING);
        try {
            String fileName = URLEncoder.encode(excelName, ExcelConstant.CHARACTER_ENCODING).replace("\\+", "%20");
            httpServletResponse.setHeader(ExcelConstant.CONTENT_DISPOSITION, ExcelConstant.DOWNLOAD_SETTING + fileName + ExcelConstant.FILE_SUFFIX);
            // 这里需要设置不关闭流
            EasyExcelFactory.write(httpServletResponse.getOutputStream(), ExcelExportEntity.class).autoCloseStream(Boolean.FALSE).sheet(sheetName)
                    .doWrite(excelExportEntities);
        }catch (Exception e) {
            logger.error("File download failed");
        }
        logger.info("File download successes");
    }
}