package com.jims.template;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.asepsis.entity.OrgSysDict;
import com.jims.common.data.StringData;
import com.jims.common.utils.LoginInfoUtils;
import com.jims.sys.api.DictServiceApi;
//import com.jims.sys.api.PriceListApi;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.Dict;
import com.jims.sys.vo.StaffGroupVo;
import com.jims.template.entity.PriceListTemplate;
import com.jims.template.api.PriceListTemplateApi;
import com.sun.jersey.multipart.FormDataParam;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* 价表模板rest
* @author lgx
* @version 2016-08-09
*/
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("priceListTemplate")
public class PriceListTemplateRest {

    @Reference(version = "1.0.0")
    private PriceListTemplateApi api;
    @Reference(version = "1.0.0")
    private DictServiceApi dictServiceApi;
//    @Reference(version = "1.0.0")
//    private PriceListApi priceListApi;

    /**
    * 检索
    * @return
    */
    @GET
    @Path("findList")
    public List<PriceListTemplate> findList(@QueryParam("area") String area,
                                            @QueryParam("itemClass") String itemClass,
                                            @QueryParam("masterId") String masterId) {
        PriceListTemplate entity = new PriceListTemplate();
        entity.setArea(area);
        entity.setItemClass(itemClass);
        entity.setMasterId(masterId);
        return api.findList(entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    @POST
    @Path("save")
    public String save(PriceListTemplate entity) {
        return api.save(entity);
    }

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    @POST
    @Path("saveList")
    public String saveList(List<PriceListTemplate> list) {
        return api.save(list);
    }

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    @GET
    @Path("delete")
    public String delete(String ids) {
        return api.delete(ids);
    }

    /**
     * 机构使用模板
     * @param id
     * @param orgId
     * @return 0 失败，1成功
     */
    @GET
    @Path("useTemplate")
    public String useTemplate(@QueryParam("id") String id,@QueryParam("orgId")String orgId){
        return api.useTemplate(id,orgId);
    }

    /**
     * 诊疗项目使用模板
     * @param area
     * @param orgId
     * @return 0 失败，1成功
     */
    @GET
    @Path("importClinicItemByTemplate")
    public String importClinicItemByTemplate(@QueryParam("area") String area,@QueryParam("orgId")String orgId){
        return api.importClinicItemByTemplate(area,orgId);
    }

    /**
     * 机构数据导入到模板
     * @param area
     * @param areaName
     * @param orgId
     * @return 0 失败，1成功，2失败，模板中有数据
     */
    @GET
    @Path("importTemplate")
    public String importTemplate(@QueryParam("area")String area,
                                 @QueryParam("areaName")String areaName,
                                 @QueryParam("orgId")String orgId,
                                 @QueryParam("masterId")String masterId){
        return api.importTemplate(area, areaName, orgId,masterId);
    }



    /**
     * 价表模板
     *
     * @param httpServletResponse
     * @param httpServletRequest
     */
    @Path("getPriceListXlsTemplate")
    @GET
    public void getStaffTemplate( @Context HttpServletResponse httpServletResponse, @Context HttpServletRequest httpServletRequest) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("价表信息");
        HSSFSheet hidden1 = workbook.createSheet("hidden1");
        HSSFSheet hidden2 = workbook.createSheet("hidden2");

        //类别
        List<Dict> dicts = dictServiceApi.findListType("BILL_ITEM_CLASS_DICT");
        String[] itemClass = new String[dicts.size()];

        for (int i = 0; i < dicts.size(); i++) {
            itemClass[i] = (String) dicts.get(i).getLabel();
        }

        //计价单位
        List<Dict> doesDict = dictServiceApi.findListType("dose_unit");
        String[] dose = new String[doesDict.size()];

        for (int i = 0; i < doesDict.size(); i++) {
            dose[i] = doesDict.get(i).getLabel();
        }


        //会计科目
        List<Dict> tallyDict = dictServiceApi.findListType("TALLY_SUBJECT_DICT");
        String[] tallys = new String[tallyDict.size()];
        for (int i = 0; i < tallyDict.size(); i++) {
            tallys[i] = tallyDict.get(i).getLabel();
        }

        //门诊收费
        List<Dict> outpDict = dictServiceApi.findListType("OUTP_RCPT_FEE_DICT");
        String[] outps = new String[outpDict.size()];
        for (int i = 0; i < outpDict.size(); i++) {
            outps[i] = outpDict.get(i).getLabel();
        }

        //住院依据
        List<Dict> inpDict = dictServiceApi.findListType("INP_RCPT_FEE_DICT");
        String[] inps = new String[inpDict.size()];
        for (int i = 0; i < inpDict.size(); i++) {
            inps[i] = inpDict.get(i).getLabel();
        }

        //病案首页
        List<Dict> mrDict = dictServiceApi.findListType("MR_FEE_CLASS_DICT");
        String[] mrs = new String[mrDict.size()];
        for (int i = 0; i < mrDict.size(); i++) {
            mrs[i] = mrDict.get(i).getLabel();
        }

        //核算项目
        List<Dict> reckDict = dictServiceApi.findListType("RECK_ITEM_CLASS_DICT");
        String[] recks = new String[reckDict.size()];
        for (int i = 0; i < reckDict.size(); i++) {
            recks[i] = reckDict.get(i).getLabel();
        }

        workbook.setSheetHidden(1, true);
        workbook.setSheetHidden(2, true);
        HSSFCellStyle headStyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headStyle.setFont(font);



        HSSFRow row = null;
        HSSFCell cell = null;

        //会计科目
        for (int i = 1, length = tallys.length; i < length; i++) {
            row = hidden1.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(tallys[i]);
        }

        //核算科目
        for (int i = 1, length = recks.length; i < length; i++) {
            row = hidden2.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(recks[i]);
        }

        //类别
        CellRangeAddressList nationsRegions = new CellRangeAddressList(1, 199, 1, 1);
        DataValidationConstraint nationConstraint = DVConstraint.createExplicitListConstraint(itemClass);
        HSSFDataValidation hssfDataValidation = new HSSFDataValidation(nationsRegions, nationConstraint);
        sheet.addValidationData(hssfDataValidation);


        //计价单位
        CellRangeAddressList sexRegions = new CellRangeAddressList(1, 199, 2, 2);
        DataValidationConstraint sexContraint = DVConstraint.createExplicitListConstraint(dose);
        HSSFDataValidation sexValidationData = new HSSFDataValidation(sexRegions, sexContraint);
        sheet.addValidationData(sexValidationData);


        //会计科目
        try{
            Name namedCell1 = workbook.createName();
            namedCell1.setNameName("hidden1");
            CellRangeAddressList addressList = new CellRangeAddressList(1, 199, 8, 8);
            DVConstraint constraint = DVConstraint.createFormulaListConstraint("hidden1!$A$1:$A$" + tallys.length);
            HSSFDataValidation validation = new HSSFDataValidation(addressList, constraint);
            sheet.addValidationData(validation);
        }catch(Exception e){

        }


        //门诊收费
        CellRangeAddressList outpsRetions = new CellRangeAddressList(1, 199, 9, 9);
        DataValidationConstraint outpsContraint = DVConstraint.createExplicitListConstraint(outps);
        HSSFDataValidation outpsValidationData = new HSSFDataValidation(outpsRetions, outpsContraint);
        sheet.addValidationData(outpsValidationData);


        //住院依据
        CellRangeAddressList inpsRetions = new CellRangeAddressList(1, 199, 10, 10);
        DataValidationConstraint inpsContraint = DVConstraint.createExplicitListConstraint(inps);
        HSSFDataValidation inpsValidationData = new HSSFDataValidation(inpsRetions, inpsContraint);
        sheet.addValidationData(inpsValidationData);

        //病案首页
        CellRangeAddressList mrsRetions = new CellRangeAddressList(1, 199, 11, 11);
        DataValidationConstraint mrsContraint = DVConstraint.createExplicitListConstraint(mrs);
        HSSFDataValidation mrsValidationData = new HSSFDataValidation(mrsRetions, mrsContraint);
        sheet.addValidationData(mrsValidationData);

        //核算科目
        Name namedCell2 = workbook.createName();
        namedCell2.setNameName("hidden2");
        CellRangeAddressList addressList2 = new CellRangeAddressList(1, 199, 12, 12);
        DVConstraint constraint2 = DVConstraint.createFormulaListConstraint("hidden2!$A$1:$A$" + recks.length);
        HSSFDataValidation validation2 = new HSSFDataValidation(addressList2, constraint2);
        sheet.addValidationData(validation2);

        for (int i = 0; i < 199; i++) {
            row = sheet.createRow(i);
            for (int j = 0; j <= 13; j++) {
                cell = row.createCell(j);
                if (i == 0) {
                    cell.setCellStyle(headStyle);
                }

                if (j == 1) {
                }
            }
        }

        row = sheet.getRow(0);
        row.getCell(0).setCellValue("项目名称");
        row.getCell(1).setCellValue("项目类别");
        row.getCell(2).setCellValue("计价单位");
        row.getCell(3).setCellValue("物价码");
        row.getCell(4).setCellValue("项目规格");
        row.getCell(5).setCellValue("基本价格");
        row.getCell(6).setCellValue("优惠价格");
        row.getCell(7).setCellValue("外宾价格");
        row.getCell(8).setCellValue("会计科目");
        row.getCell(9).setCellValue("门诊收费");
        row.getCell(10).setCellValue("住院依据");
        row.getCell(11).setCellValue("病案首页");
        row.getCell(12).setCellValue("核算科目");
        row.getCell(13).setCellValue("拼音码");

        httpServletResponse.setContentType("application/vnd.ms-excel");
        httpServletResponse.setHeader("Content-disposition", "attachment;filename=price_list.xls");
        OutputStream ouputStream = httpServletResponse.getOutputStream();
        workbook.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }



    /**
     * 导入模板
     *
     * @param file
     * @param request
     * @param response
     * @return
     * @throws IOException
     */

    @POST
    @Path("up-xls")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public StringData uploadXls(@QueryParam("masterId") String masterId,@FormDataParam("file") InputStream file, @Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
//        HSSFWorkbook workbook = new HSSFWorkbook(file);
//        HSSFSheet sheetAt = workbook.getSheetAt(0);
//        List<StaffGroupVo> infos = new ArrayList<StaffGroupVo>();
//        StaffGroupVo priceDictListVo = null;
//
//        Row nRow = null;
//        //变量  重复
//        int repeat = 0;
//        //记录第几行重复
//        String num;
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
//            sheetAt = workbook.getSheetAt(i);
//            int end = sheetAt.getLastRowNum();
//
//            for (int j = 1; j <= end; j++) {
//                nRow = sheetAt.getRow(j);
//                //判断第一个单元格是否为空，如果为空  整行都不插入
//                if (nRow.getCell(0) == null || nRow.getCell(0).equals("") || nRow.getCell(0).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
//                    sb.append(j + "  ");
//                    repeat++;
//                    continue;
//                }
//                if (nRow.getCell(1) == null || nRow.getCell(1).equals("") || nRow.getCell(1).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
//                    continue;
//                }
//
//                priceDictListVo = new StaffGroupVo();
//                priceDictListVo.setItemName(getCellValue(nRow.getCell(0)));
//                String itemClass = dictServiceApi.getValue("BILL_ITEM_CLASS_DICT", getCellValue(nRow.getCell(1)));
//                priceDictListVo.setItemClass(itemClass);
//                //项目代码
//                String res = priceListApi.findSeqences();
//                if (res.length() <= 1) {
//                    res = "00" + res + itemClass;
//                } else if (res.length() == 2) {
//                    res = "0" + res + itemClass;
//                } else if (res.length() == 3) {
//                    res = res + itemClass;
//                }
//
//                priceDictListVo.setItemCode(res);
//
//                if(nRow.getCell(2) == null || nRow.getCell(2).equals("") || nRow.getCell(2).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
//                    sb.append(j + "  ");
//                    repeat++;
//                    continue;
//                }
//                priceDictListVo.setPerformedBy("");
//                String units = dictServiceApi.getValue("dose_unit", getCellValue(nRow.getCell(2)));
//
//
//                priceDictListVo.setUnits(units);
//                priceDictListVo.setMaterialCode(getCellValue(nRow.getCell(3)));
//                priceDictListVo.setItemSpec(getCellValue(nRow.getCell(4)));
//                //价格
//                Double price = Double.parseDouble(getCellValue(nRow.getCell(5)));
//                priceDictListVo.setPrice(price);
//                Double preferPrice = Double.parseDouble(getCellValue(nRow.getCell(6)));
//                priceDictListVo.setPreferPrice(preferPrice);
//                Double foreignerPrice = Double.parseDouble(getCellValue(nRow.getCell(7)));
//                priceDictListVo.setForeignerPrice(foreignerPrice);
//
//                //会计科目
//                if(nRow.getCell(8) == null || nRow.getCell(8).equals("") || nRow.getCell(8).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
//                    sb.append(j + "  ");
//                    repeat++;
//                    continue;
//                }
//                String tally = dictServiceApi.getValue("TALLY_SUBJECT_DICT", getCellValue(nRow.getCell(8)));
//                priceDictListVo.setSubjCode(tally);
//
//                //门诊收费
//                if(nRow.getCell(9) == null || nRow.getCell(9).equals("") || nRow.getCell(9).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
//                    sb.append(j + "  ");
//                    repeat++;
//                    continue;
//                }
//                String outp = dictServiceApi.getValue("OUTP_RCPT_FEE_DICT", getCellValue(nRow.getCell(9)));
//                priceDictListVo.setUnits(outp);
//
//                //住院依据
//                if(nRow.getCell(10) == null || nRow.getCell(10).equals("") || nRow.getCell(10).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
//                    sb.append(j + "  ");
//                    repeat++;
//                    continue;
//                }
//                String inp = dictServiceApi.getValue("INP_RCPT_FEE_DICT", getCellValue(nRow.getCell(10)));
//                priceDictListVo.setClassOnInpRcpt(inp);
//
//                //病案首页
//                if(nRow.getCell(11) == null || nRow.getCell(11).equals("") || nRow.getCell(11).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
//                    sb.append(j + "  ");
//                    repeat++;
//                    continue;
//                }
//                String mr = dictServiceApi.getValue("MR_FEE_CLASS_DICT", getCellValue(nRow.getCell(11)));
//                priceDictListVo.setClassOnMr(mr);
//
//                //核算科目
//                if(nRow.getCell(12) == null || nRow.getCell(12).equals("") || nRow.getCell(12).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
//                    sb.append(j + "  ");
//                    repeat++;
//                    continue;
//                }
//                String reck = dictServiceApi.getValue("RECK_ITEM_CLASS_DICT", getCellValue(nRow.getCell(12)));
//                priceDictListVo.setClassOnReckoning(reck);
//
//
//                //拼音码
//                priceDictListVo.setInputCode(getCellValue(nRow.getCell(13)).toUpperCase());
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String date = formatter.format(new Date());
//                priceDictListVo.setStartDate(date);
//                priceDictListVo.setMasterId(masterId);
//                infos.add(priceDictListVo);
//            }
//
//        }
//
//        String code = api.saveListData(infos);
//
//        String error = "成功插入<font color='red' > " +code+ " </font>条数据 失败<font color='red'> " +  repeat  + " </font>条";
//        if (repeat > 0) {
//            error += "    第 <font color='red' > " + sb.toString() + " </font>行插入失败<br><br>　请检查数据，是否不允许为空　　　或者数据不合法";
//        }
        StringData stringData = new StringData();
//        stringData.setData(error);
        return stringData;

    }


    //判断单元格的类型
    private String getCellValue(Cell cell) {
        String cellValue = "";
        DecimalFormat df = new DecimalFormat("#");
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                cellValue = cell.getRichStringCellValue().getString().trim();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                cellValue = df.format(cell.getNumericCellValue()).toString();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
                cellValue = "";
        }
        return cellValue;
    }



}