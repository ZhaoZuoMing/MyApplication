package donm.sky.hotel.htservice;

import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.dmonline.R;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import donm.sky.hotel.model.CardData;
import donm.sky.hotel.model.CommitOrderResult;
import donm.sky.hotel.model.HotelDataValidateResult;
import donm.sky.hotel.model.NightlyRates;
import donm.sky.hotel.model.ResultObject;
import f.sky.flight.core.WebServUtil;
import f.sky.flight.model.B_TouristDOObj;

/**
 * Created by Administrator on 2017/3/6/006.
 * 酒店订单提交
 */

public class CommitSer extends WebServUtil {
        private final  static  String TAG = "CommitSer：";
        @Nullable
        public static CommitOrderResult commitHoteData(int Clientid, int corpId, int DepId, int BizId, String Hotelid, String HotelCode, String HotelName, String HotelEName, String BrandName, String HotelAddress, String HotelEAddress,
                                                       String RoomTypeName, String RoomTypeEName, String RatePlanCode, String Status, int Rank, String Cityid, String CityName, String SouceType,
                                                       String SellType, String Payment, String CostCenter, String CostNumber, String Vendor, int NumberOfUnits, int NumberOfDate, int GuestCount, String CheckInDate,
                                                       String CheckOutDate, int Bookid, String BookName, String BookDate, int Confirmid, String ConfirmDate, String PayType,
                                                       String EarliestArrivalTime, String LaterArrivalTime, String CreateTime, String ylEarliestArrivalTime, String ylLatestArrivalTime,
                                                       int Breakfast, int net, String HotelPhone, String invoicemode,
                                                       String ContactPersonName, String ContactPhoneNumber, String ContactEmail, List<B_TouristDOObj> enterPersons
                                              , String CustomerType, List<NightlyRates> nightlyRates, String phoneIp, boolean IsGuaranteeOrCharged, CardData card,
                                                       String RoomTypeId, String CurrencyCode, String TotalPrice, double elTotalPrice,String RoomId){
            PostMethod postMethod = new PostMethod(WebServUtil.HOTEL_SERVICE);
             try {
                 byte b[] = builderCommitData(Clientid, corpId,DepId,BizId,Hotelid,HotelCode,HotelName,HotelEName, BrandName,HotelAddress,HotelEAddress,RoomTypeName, RoomTypeEName,RatePlanCode, Status,
                         Rank, Cityid, CityName,SouceType, SellType, Payment,CostCenter,CostNumber,Vendor,NumberOfUnits,NumberOfDate, GuestCount, CheckInDate,CheckOutDate, Bookid,BookName,BookDate,
                         Confirmid,ConfirmDate,PayType,EarliestArrivalTime,LaterArrivalTime, CreateTime,ylEarliestArrivalTime,ylLatestArrivalTime,Breakfast,net,HotelPhone,invoicemode,
                         ContactPersonName,ContactPhoneNumber, ContactEmail,enterPersons, CustomerType,nightlyRates,phoneIp,IsGuaranteeOrCharged,card,
                         RoomTypeId,CurrencyCode,TotalPrice,elTotalPrice,RoomId).getBytes(ENCODE);
//                      //提交的参数

                String data =  builderCommitData(Clientid, corpId,DepId,BizId,Hotelid,HotelCode,HotelName,HotelEName, BrandName,HotelAddress,HotelEAddress,RoomTypeName, RoomTypeEName,RatePlanCode, Status,
                        Rank, Cityid, CityName,SouceType, SellType, Payment,CostCenter,CostNumber,Vendor,NumberOfUnits,NumberOfDate, GuestCount, CheckInDate,CheckOutDate, Bookid,BookName,BookDate,
                        Confirmid,ConfirmDate,PayType,EarliestArrivalTime,LaterArrivalTime, CreateTime,ylEarliestArrivalTime,ylLatestArrivalTime,Breakfast,net,HotelPhone,invoicemode,
                        ContactPersonName,ContactPhoneNumber, ContactEmail,enterPersons, CustomerType,nightlyRates,phoneIp,IsGuaranteeOrCharged,card,
                        RoomTypeId,CurrencyCode,TotalPrice,elTotalPrice,RoomId);
                   int index = data.indexOf("<ELCondition>");
                   int lastIndex = data.indexOf("</ELCondition>");
                    data = data.substring(index,lastIndex+"</ELCondition>".length());
//                  Log.e(TAG, "打印艺龙数据---:" +data);

                 InputStream is = new ByteArrayInputStream(b, 0, b.length);
                 RequestEntity re = new InputStreamRequestEntity(is, b.length,  CONTENT_TYPE);

                 postMethod.setRequestEntity(re);
                 HttpClient httpClient = new HttpClient();
                 int statusCode = httpClient.executeMethod(postMethod);
                 if (statusCode == WebServUtil.HTTP_STATUS_OK) {
                     String response = postMethod.getResponseBodyAsString();
                     Log.e("CommitSer:  ", "------酒店预订结果--： "+response);
                     return parseOrderResult(response) ;
                 }
             }catch (Exception e){
                 e.printStackTrace();
                 Log.e(TAG, "commitHoteData: "+e.getMessage());
             }


            return null;
        }

    /**
     * 解析数据
     * @param response
     * @return
     */
    private static CommitOrderResult parseOrderResult(String response) {

        CommitOrderResult orderResult = new CommitOrderResult();
        Element root;
        Document doc;
        SAXReader reader = new SAXReader();
        try {
            doc = reader.read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            root = doc.getRootElement();
            Element obj = root.element("Body")
                    .element("CommitOrderResponse")
                    .element("CommitOrderResult");
            orderResult.setMessage(obj.elementTextTrim("Message"));
            orderResult.setElapsedMilliseconds(parseInt(obj.elementTextTrim("ElapsedMilliseconds")));
            orderResult.setSuccess(parseBoolean(obj.elementTextTrim("Success")));

            //-------ID及其他----
            Element result = obj.element("ResultObject");
            orderResult.setID(parseInt(result.elementTextTrim("ID")));
            orderResult.setBookId(parseInt(result.elementTextTrim("BookID")));
            orderResult.setDepId(parseInt(result.elementTextTrim("DepID")));
            orderResult.setAuditID(parseInt(result.elementTextTrim("AuditID")));
            orderResult.setAuditMail(result.elementTextTrim("AuditMail"));
            orderResult.setStatus(parseInt(result.elementTextTrim("Status")));
            orderResult.setInsertDate(result.elementTextTrim("insertDate"));
            orderResult.setAuditDate(result.elementTextTrim("AuditDate"));
            orderResult.setReason(result.elementText("Reason"));
            orderResult.setApplyStatus(parseInt(result.elementTextTrim("ApplyStatus")));

        }catch (Exception e){
            Log.e(TAG, "parseOrderResult: "+e.getMessage() );
        }

        return  orderResult;
    }

    /**
     * 构建提交参数
     * @param
     * @return
     */
        private static String builderCommitData(int Clientid, int corpId, int DepId, int BizId,
                                                String Hotelid, String HotelCode, String HotelName, String HotelEName, String BrandName, String HotelAddress, String HotelEAddress,
                                                String RoomTypeName, String RoomTypeEName, String RatePlanCode, String Status, int Rank, String Cityid, String CityName, String SouceType,
                                                String SellType, String Payment, String CostCenter, String CostNumber, String Vendor, int NumberOfUnits, int NumberOfDate, int GuestCount,
                                                String CheckInDate,
                                                String CheckOutDate,
                                                int Bookid, String BookName,
                                                String BookDate, int Confirmid,
                                                String ConfirmDate, String PayType,
                                                String EarliestArrivalTime, String LaterArrivalTime, String CreateTime,String ylEarliestArrivalTime,String ylLatestArrivalTime,
                                                int Breakfast, int net, String HotelPhone, String invoicemode,
                                                String ContactPersonName, String ContactPhoneNumber, String ContactEmail,
                                                List<B_TouristDOObj> enterPersons, String CustomerType, List<NightlyRates> nightlyRates,
                                                String phoneIp, boolean IsGuaranteeOrCharged,
                                                CardData card,String RoomTypeId,String CurrencyCode,String TotalPrice,double elTotalPrice,String RoomId){
            StringBuilder  soapRequestData = buildSoapHeader();
            soapRequestData.append("<"+WebServUtil.COMMIT_HOTEL_DATA+" xmlns=\""+ WebServUtil.TARGET_NAME_SPACE + "\">");
            soapRequestData.append("<sap>");
            soapRequestData.append("<UserID>" + WebServUtil.HBE_USER_NAME + "</UserID>");
            soapRequestData.append("<Password>" +WebServUtil.HBE_USER_PAW+ "</Password>");
            soapRequestData.append("<Patameter>");
            soapRequestData.append("<orderList>");
            soapRequestData.append("<Horder>");
             soapRequestData.append("<DeptSerialNo>"+"111"+"</DeptSerialNo>");
             soapRequestData.append("<OrderType>"+0+"</OrderType>");
             soapRequestData.append("<RelationOrderID>"+0+"</RelationOrderID>");
             soapRequestData.append("<RelationApplyID>"+0+"</RelationApplyID>");
             soapRequestData.append("<PurchaseID>"+0+"</PurchaseID>");
             soapRequestData.append("<PurchaseCode>"+0+"</PurchaseCode>");
             soapRequestData.append("<Payment>"+Payment+"</Payment>");// not null
             soapRequestData.append("<PayType>"+Payment+"</PayType>");//???
             soapRequestData.append("<CostCenter>"+CostCenter+"</CostCenter>");
             soapRequestData.append("<CostNumber>"+CostNumber+"</CostNumber>");//--not null
             soapRequestData.append("<ChangeRule>"+"变更规则"+"</ChangeRule>");
             soapRequestData.append("<InvoiceMode>"+"ELong"+"</InvoiceMode>");
             soapRequestData.append("<ConfirmInfo>"+" "+"</ConfirmInfo>");
            //----pass--02


            soapRequestData.append("<Clientid>"+Clientid+"</Clientid>");
            soapRequestData.append("<BizDeptid>"+BizId+"</BizDeptid>");//Biz部门Id
            soapRequestData.append("<Deptid>"+DepId+"</Deptid>");//员工所在的部门Id
            soapRequestData.append("<Corpid>"+corpId+"</Corpid>");


            //---pass--03
            soapRequestData.append("<NumberOfUnits>"+NumberOfUnits+"</NumberOfUnits>");//房间数量
            soapRequestData.append("<NumberOfDate>"+NumberOfDate+"</NumberOfDate>");//入住天数
            soapRequestData.append("<IsPerRoom>"+1+"</IsPerRoom>");
            soapRequestData.append("<GuestCount>"+GuestCount+"</GuestCount>");

//----pass--04
            soapRequestData.append("<Bookid>"+Bookid+"</Bookid>");//当前登录的用户名
            soapRequestData.append("<BookName>"+BookName+"</BookName>");
            soapRequestData.append("<BookDate>"+BookDate+"</BookDate>");

            soapRequestData.append("<Confirmid>"+1+"</Confirmid>");// not null
            soapRequestData.append("<ConfirmName>"+"系统自动"+"</ConfirmName>");// not null
            soapRequestData.append("<ConfirmDate>"+BookDate+"</ConfirmDate>");// not null
            soapRequestData.append("<Cancelid>"+0+"</Cancelid>");// not null
            soapRequestData.append("<CancelName>"+ContactPersonName+"</CancelName>");// not null
            soapRequestData.append("<CancelDate>"+"  "+"</CancelDate>");// not null
            soapRequestData.append("<SerialNo>"+0+"</SerialNo>");//支付流水号

            soapRequestData.append("<CashStatus>"+1+"</CashStatus>");// not null
            soapRequestData.append("<CashDate>"+BookDate+"</CashDate>");//支付日期not null
            soapRequestData.append("<Award1Status>"+99+"</Award1Status>");// not null 奖励状态
            soapRequestData.append("<AwardDate>"+BookDate+"</AwardDate>");// not null  奖励日期
            soapRequestData.append("<Deposit>"+0+"</Deposit>");
            soapRequestData.append("<VendorDeposit>"+0+"</VendorDeposit>");
            soapRequestData.append("<LatestPayDate>"+"2017-12-12"+"</LatestPayDate>");// not null

            soapRequestData.append("<BalanceNo>"+"a"+"</BalanceNo>");//结算号
            soapRequestData.append("<Balanceid>"+1+"</Balanceid>");//结算人
            soapRequestData.append("<BalanceStatus>"+0+"</BalanceStatus>");// not null
            soapRequestData.append("<BalanceDate>"+BookDate+"</BalanceDate>");
            soapRequestData.append("<Commid>"+1+"</Commid>");//返佣状态// not null
            soapRequestData.append("<CommStatus>"+1+"</CommStatus>");// not null
            soapRequestData.append("<CommDate>"+BookDate+"</CommDate>");// not null

            //---pass--05
            soapRequestData.append("<ArrivalTime>"+EarliestArrivalTime+"</ArrivalTime>");//最早抵店时间
            soapRequestData.append("<LaterArrivalTime>"+LaterArrivalTime+"</LaterArrivalTime>");
            soapRequestData.append("<CreateTime>"+CreateTime+"</CreateTime>");


            //----pass--06
            soapRequestData.append("<Breakfast>"+Breakfast+"</Breakfast>");// not null
            soapRequestData.append("<Net>"+net+"</Net>");// not null
            soapRequestData.append("<Sms>"+1+"</Sms>");// not null
            soapRequestData.append("<Email>"+1+"</Email>");// not null
            soapRequestData.append("<ConfirmEmail>"+0+"</ConfirmEmail>");// not null
            soapRequestData.append("<SpecialRequest>"+"无"+"</SpecialRequest>");
            soapRequestData.append("<CheckID>"+0+"</CheckID>");
            soapRequestData.append("<VendorOrderShowStatus>"+0+"</VendorOrderShowStatus>");
            soapRequestData.append("<Vendor>"+Vendor+"</Vendor>");//价格来源ELong

            soapRequestData.append("<Orderid>"+0+"</Orderid>");
            soapRequestData.append("<ApplyID>"+0+"</ApplyID>");
            soapRequestData.append("<Hotelid>"+Hotelid+"</Hotelid>");
            soapRequestData.append("<HotelCode>"+HotelCode+"</HotelCode>");
            soapRequestData.append("<HotelName>"+HotelName+"</HotelName>");
            soapRequestData.append("<HotelEName>"+HotelEName+"</HotelEName>");
            soapRequestData.append("<BrandName>"+BrandName+"</BrandName>");  /**品牌**/
            soapRequestData.append("<HotelAddress>"+HotelAddress+"</HotelAddress>");
            soapRequestData.append("<HotelEAddress>"+HotelEAddress+"</HotelEAddress>");
            soapRequestData.append("<RoomTypeName>"+RoomTypeName+"</RoomTypeName>");
            soapRequestData.append("<RoomTypeEName>"+"test_ename"+"</RoomTypeEName>");//RoomTypeEName
            soapRequestData.append("<RatePlanCode>"+RatePlanCode+"</RatePlanCode>");
            soapRequestData.append("<Status>"+Status+"</Status>");
            soapRequestData.append("<Rank>"+Rank+"</Rank>");
            soapRequestData.append("<Cityid>"+0+"</Cityid>");
            soapRequestData.append("<CityCode>"+Cityid+"</CityCode>");
            soapRequestData.append("<CityName>"+"上海"+"</CityName>");

            //  ---pass--01
            soapRequestData.append("<SouceType>"+0+"</SouceType>");
            soapRequestData.append("<SellType>"+0+"</SellType>");
            soapRequestData.append("<ExternalNo>"+""+"</ExternalNo>");
            soapRequestData.append("<PNR>"+"ppp"+"</PNR>");

            soapRequestData.append("<CheckInDate>"+CheckInDate+"</CheckInDate>");
            soapRequestData.append("<CheckOutDate>"+CheckOutDate+"</CheckOutDate>");

            soapRequestData.append("<CancelReason>"+" "+"</CancelReason>");
            soapRequestData.append("<FallowUpHotel>"+" "+"</FallowUpHotel>");//后续酒店
            soapRequestData.append("<CheckName>"+ContactPersonName+"</CheckName>");//核对人
            soapRequestData.append("<CheckDate>"+BookDate+"</CheckDate>");//核对日期
            soapRequestData.append("<HotelPhone>"+HotelPhone+"</HotelPhone>");
            soapRequestData.append("<invoicemode>"+invoicemode+"</invoicemode>");//发票提供方
            soapRequestData.append("<confirminfo>"+""+"</confirminfo>");//人工确认信息
/***  联系人落在本地---pass--07**/
            soapRequestData.append("<ColFkHordercoReferenceHorder>");
              appendHorderContact( soapRequestData, ContactPersonName, ContactPhoneNumber, ContactEmail);
            soapRequestData.append("</ColFkHordercoReferenceHorder>");
/**服务费用 暂时全部无值--pass--08--**/
            soapRequestData.append("<FkHorderfuReferenceHorder>");
             appendFkHorderfuReferenceHorder( soapRequestData, TotalPrice);
            soapRequestData.append("</FkHorderfuReferenceHorder>");

//            soapRequestData.append("<FkHorderguReferenceHorder>");
//            soapRequestData.append("<Orderid>"+0+"</Orderid>");
//            soapRequestData.append("<CardHolderidNumber>"+1+"</CardHolderidNumber>");
//            soapRequestData.append("<GuaranteeAmount>"+0+"</GuaranteeAmount>");
//            soapRequestData.append("<CancelPenaltyStart/>");
//            soapRequestData.append("<CancelPenaltyEnd/>");
//            soapRequestData.append("<IsDeleted>"+false+"</IsDeleted>");
//            soapRequestData.append("<IsChanged>"+false+"</IsChanged>");
//            soapRequestData.append("</FkHorderguReferenceHorder>");


            //旅客----pass--09---

/**----入住人信息**/
            soapRequestData.append("<ColFkHOrdergReferenceHOrder2>");
            for (int i=0;i<enterPersons.size();i++){
                B_TouristDOObj enterPerson = enterPersons.get(i);
                 appendHorderGuest(soapRequestData,enterPerson);
                }
            soapRequestData.append("</ColFkHOrdergReferenceHOrder2>");

//            soapRequestData.append("<OrderFundDetail>");
//             appendH_OrderFundDetail(soapRequestData,CheckInDate,TotalPrice,elTotalPrice);
//            soapRequestData.append("</OrderFundDetail>");

//            soapRequestData.append("<ColFkHorderloReferenceHorder>");
//             appendHorderLog(soapRequestData,BookName,ConfirmDate);
//            soapRequestData.append("</ColFkHorderloReferenceHorder>");

            soapRequestData.append("<OrderOtherInfo>");
              appendOrderOtherInfo(soapRequestData);
            soapRequestData.append("</OrderOtherInfo>");

//            soapRequestData.append("<GuaranteeAndCancelDetail>");
//              appendGuaranteeAndCancelDetail(soapRequestData,CheckInDate);
//            soapRequestData.append("</GuaranteeAndCancelDetail>");

            soapRequestData.append("<IsDeleted>"+false+"</IsDeleted>");
            soapRequestData.append("<IsChanged>"+false+"</IsChanged>");
            soapRequestData.append("</Horder>");
            soapRequestData.append("</orderList>");
/***---orderlist结束**/
            soapRequestData.append("<PayOnline>"+false+"</PayOnline>");
/***申请单--****/
            soapRequestData.append("<bApply>");
              appendBAplay(soapRequestData,Bookid,DepId, ContactEmail,EarliestArrivalTime, LaterArrivalTime);
            soapRequestData.append("</bApply>");
/*****艺龙<ELCondition>-- *****/
            soapRequestData.append("<ELCondition>");
            appendELCondition(soapRequestData, phoneIp,  IsGuaranteeOrCharged,card,
                     Hotelid, RoomTypeId, RatePlanCode, PayType, CheckInDate,
                     CheckOutDate, CustomerType, NumberOfUnits , GuestCount, EarliestArrivalTime,
                     LaterArrivalTime, CurrencyCode, elTotalPrice, TotalPrice, ContactPersonName,
                     ContactEmail, nightlyRates,  enterPersons);
            soapRequestData.append("</ELCondition>");
            soapRequestData.append("</Patameter>");
            soapRequestData.append("</sap>");
            soapRequestData.append("</" + WebServUtil.COMMIT_HOTEL_DATA + ">");
            return buildSoapFooter(soapRequestData).toString();
 }



    /**
     *
     * @param soapRequestData
     */
    private static void appendGuaranteeAndCancelDetail(StringBuilder soapRequestData,String checkIndata) {
        soapRequestData.append("<Orderid>"+0+"</Orderid>");
        soapRequestData.append("<Guarantee>"+0+"</Guarantee>");
        soapRequestData.append("<GuaranteeChangeRuleHour>"+0+"</GuaranteeChangeRuleHour>");
        soapRequestData.append("<PrepayChangeRule>"+"PrepayNeedSomeDay"+"</PrepayChangeRule>");
        soapRequestData.append("<PrepayDateNum>"+checkIndata+"</PrepayDateNum>");
        soapRequestData.append("<PrepayTime>"+"18:00"+"</PrepayTime>");
        soapRequestData.append("<GuaranteeCost>"+0+"</GuaranteeCost>");
        soapRequestData.append("<Status>"+1+"</Status>");
        soapRequestData.append("<GuaranteeChangeRuleTime/>");
        soapRequestData.append("<GuaranteeInfo/>");
        soapRequestData.append("<GuaranteeChangeRule/>");
        soapRequestData.append("<PrepayDeductFeesBefore>"+0+"</PrepayDeductFeesBefore>");
        soapRequestData.append("<PrepayDeductNumBefore>"+0.0+"</PrepayDeductNumBefore>");
        soapRequestData.append("<PrepayCashScaleFirstAfter>"+"Percent"+"</PrepayCashScaleFirstAfter>");
        soapRequestData.append("<PrepayDeductFeesAfter>"+1+"</PrepayDeductFeesAfter>");
        soapRequestData.append("<PrepayDeductNumAfter>"+100.0+"</PrepayDeductNumAfter>");
        soapRequestData.append("<PrepayCashScaleFirstBefore>"+"FristNight"+"</PrepayCashScaleFirstBefore>");
        soapRequestData.append("<PrepayHour>"+60+"</PrepayHour>");
        soapRequestData.append("<PrepayHour2>"+0+"</PrepayHour2>");
        soapRequestData.append("<GuaranteeStartDate/>");
        soapRequestData.append("<GuaranteeEndDate/>");
        soapRequestData.append("<GuaranteeWeekSet/>");
        soapRequestData.append("<GuaranteeStartTime/>");
        soapRequestData.append("<GuaranteeEndTime/>");
        soapRequestData.append("<GuaranteeType/>");
        soapRequestData.append("<GuaranteeType/>");
        soapRequestData.append("<GuaranteeIsTimeGuarantee>"+0+"</GuaranteeIsTimeGuarantee>");
        soapRequestData.append("<GuaranteeIsTomorrow>"+0+"</GuaranteeIsTomorrow>");
        soapRequestData.append("<GuaranteeIsAmountGuarantee>"+0+"</GuaranteeIsAmountGuarantee>");
        soapRequestData.append("<GuaranteeAmount>"+0+"</GuaranteeAmount>");
        soapRequestData.append("<IsDeleted>"+false+"</IsDeleted>");
        soapRequestData.append("<IsChanged>"+false+"</IsChanged>");

    }

    /**
     * 需要添加的异常参数
     * @param soapRequestData
     */
    /**

     <wish_tip_invoice_account />
     <wishtip_invoice_no />
     <wishtip_invoice_type />
     <check_out_early_sign />
     <action>changeStatusWillHotalSure</action>
     <person_wash_tip />
     <public_wash_tip />
     <person_order_subtotal />
     <room_tax_amount />
     <wash_tip_tax_amount />
     <room_tax_rate />
     <wash_tip_tax_rate />
     <cityId />
     <transaction_no />
     <pre_property />
     <property />
     <old_transaction_no />
     <travel_itinerary_no />
     <travel_itinerary_pre />
     <old_settle_mode />
     <job_position />
     <broadband_costs />
     <local_checkin_date />
     <local_checkout_time />
     <fact_checkout_time />
     <currency />
     <exchange_rate />
     <for_currency_unit_price />
     <exceeded />
     <budget_document />
     <public_status />
     <unified_payment_no />
     <unified_payment_status />
     <last_update_dat />
     <fact_checkout />
     <settle_checkin_date />
     <settle_checkout_date />
     <settle_rooms />
     <settle_nights />
     <settle_room_account />
     <fact_settle_room_account />
     <settle_wash_tip />
     <settle_breakfase />
     <settle_fast />
     <settle_commu />
     <settle_other />
     <settle_total />
     <settle_service />
     <hotal_invoice_no />
     <hotal_invoice_type />
     <invoice_account />
     <invoice_rate />
     <is_gds />
     <personSettleWashTip />
     <tipBudgetDocument />
     <booking_user />
     <booking_user_no />
     <itinerary_city />
     <settlement_mode />
     <pay_mode />
     <guarantee_mode />
     <guarantee_amount />
     <break_amount />
     <hotel_property />
     <breakfast_count />
     <reimburse_status />
     <reimburse_no />
     <status>15</status>
     <create_date />
     <order_no />
     <old_order_no />
     <payment_status />
     <checkin_user />
     <checkin_user_count />
     <hotel_id />
     <hotel_name />
     <hotel_city />
     <hotel_address />
     <hotel_tel />
     <room_type />
     <checkin_date />
     <checkout_time />
     <rooms_counts />
     <nirght_counts />
     <unit_price />
     <service_costs />
     <order_subtotal />
     <for_order_subtotal />
     <remarks />
     <IsDeleted>false</IsDeleted>
     <IsChanged>false</IsChanged>
     * @param soapRequestData
     */
    private static void appendOrderOtherInfo(StringBuilder soapRequestData) {

        soapRequestData.append("<Orderid>"+0+"</Orderid>");
        soapRequestData.append("<Upload>"+1+"</Upload>");
        soapRequestData.append("<settle_public_room_account/>");
        soapRequestData.append("<settle_person_room_account/>");
        soapRequestData.append("<settle_person_wish_account/>");
        soapRequestData.append("<settle_public_wish_account/>");
        soapRequestData.append("<person_settle_total/>");
        soapRequestData.append("<wish_tip_invoice_account/>");
        soapRequestData.append("<wishtip_invoice_type/>");
        soapRequestData.append("<check_out_early_sign/>");
        soapRequestData.append("<person_wash_tip/>");
        soapRequestData.append("<public_wash_tip/>");
        soapRequestData.append("<person_order_subtotal/>");
        soapRequestData.append("<action>"+"changeStatusWillHotalSure"+"</action>");
        soapRequestData.append("<room_tax_amount/>");
        soapRequestData.append("<wash_tip_tax_amount/>");
        soapRequestData.append("<room_tax_rate/>");
        soapRequestData.append("<wash_tip_tax_rate/>");
        soapRequestData.append("<cityId/>");
        soapRequestData.append("<transaction_no/>");
        soapRequestData.append("<pre_property/>");
        soapRequestData.append("<old_transaction_no/>");
        soapRequestData.append("<travel_itinerary_no/>");
        soapRequestData.append("<travel_itinerary_pre/>");
        soapRequestData.append("<old_settle_mode/>");
        soapRequestData.append("<job_position/>");
        soapRequestData.append("<broadband_costs/>");
        soapRequestData.append("<local_checkin_date/>");
        soapRequestData.append("<local_checkout_time/>");
        soapRequestData.append("<fact_checkout_time/>");
        soapRequestData.append("<currency/>");
        soapRequestData.append("<exchange_rate/>");
        soapRequestData.append("<for_currency_unit_price/>");
        soapRequestData.append("<exceeded/>");
        soapRequestData.append("<budget_document/>");
        soapRequestData.append("<public_status/>");
        soapRequestData.append("<unified_payment_no/>");
        soapRequestData.append("<unified_payment_status/>");
        soapRequestData.append("<unified_payment_no/>");
        soapRequestData.append("<unified_payment_status/>");
        soapRequestData.append("<last_update_dat/>");
        soapRequestData.append("<fact_checkout/>");
        soapRequestData.append("<settle_checkin_date/>");
        soapRequestData.append("<settle_checkout_date/>");
        soapRequestData.append("<settle_rooms/>");
        soapRequestData.append("<settle_nights/>");
        soapRequestData.append("<settle_room_account/>");
        soapRequestData.append("<fact_settle_room_account/>");
        soapRequestData.append("<settle_wash_tip/>");
        soapRequestData.append("<settle_breakfase/>");
        soapRequestData.append("<settle_fast/>");
        soapRequestData.append("<settle_commu/>");
        soapRequestData.append("<settle_other/>");
        soapRequestData.append("<settle_total/>");
        soapRequestData.append("<settle_service/>");
        soapRequestData.append("<hotal_invoice_no/>");
        soapRequestData.append("<hotal_invoice_type/>");
        soapRequestData.append("<invoice_account/>");
        soapRequestData.append("<invoice_rate/>");
        soapRequestData.append("<is_gds/>");
        soapRequestData.append("<personSettleWashTip/>");
        soapRequestData.append("<tipBudgetDocument/>");
        soapRequestData.append("<booking_user/>");
        soapRequestData.append("<booking_user_no/>");
        soapRequestData.append("<itinerary_city/>");
        soapRequestData.append("<settlement_mode/>");
        soapRequestData.append("<pay_mode/>");
        soapRequestData.append("<guarantee_mode/>");
        soapRequestData.append("<guarantee_amount/>");
        soapRequestData.append("<break_amount/>");
        soapRequestData.append("<hotel_property/>");
        soapRequestData.append("<breakfast_count/>");
        soapRequestData.append("<reimburse_status/>");
        soapRequestData.append("<reimburse_no/>");
        soapRequestData.append("<reimburse_no/>");
        soapRequestData.append("<status>"+15+"</status>");
        soapRequestData.append("<create_date/>");
        soapRequestData.append("<order_no/>");
        soapRequestData.append("<old_order_no/>");
        soapRequestData.append("<payment_status/>");
        soapRequestData.append("<checkin_user/>");
        soapRequestData.append("<checkin_user_count/>");
        soapRequestData.append("<hotel_id/>");
        soapRequestData.append("<hotel_name/>");
        soapRequestData.append("<hotel_city/>");
        soapRequestData.append("<hotel_address/>");
        soapRequestData.append("<hotel_tel/>");
        soapRequestData.append("<room_type/>");
        soapRequestData.append("<checkin_date/>");
        soapRequestData.append("<checkout_time/>");
        soapRequestData.append("<rooms_counts/>");
        soapRequestData.append("<nirght_counts/>");
        soapRequestData.append("<unit_price/>");
        soapRequestData.append("<service_costs/>");
        soapRequestData.append("<order_subtotal/>");
        soapRequestData.append("<for_order_subtotal/>");
        soapRequestData.append("<remarks/>");
        soapRequestData.append("<IsDeleted>"+false+"</IsDeleted>");
        soapRequestData.append("<IsChanged>"+false+"</IsChanged>");
    }

    private static void appendHorderLog(StringBuilder soapRequestData, String bookName, String confirmDate) {
        soapRequestData.append("<HorderLog>");
          soapRequestData.append("<Id>"+0+"</Id>");
          soapRequestData.append("<Orderid>"+0+"</Orderid>");
          soapRequestData.append("<Detail>"+"提交订单"+"</Detail>");
          soapRequestData.append("<UserName>"+bookName+"</UserName>");
          soapRequestData.append("<LogTime>"+confirmDate+"</LogTime>");
          soapRequestData.append("<IsDeleted>"+false+"</IsDeleted>");//核对日期
          soapRequestData.append("<IsChanged>"+false+"</IsChanged>");//核对日期
        soapRequestData.append("</HorderLog>");


    }

    private static void appendH_OrderFundDetail(StringBuilder soapRequestData,String checkinData,String ClientNetPrice,double NetPrice) {
        soapRequestData.append("<H_OrderFundDetail>");//核对日期
        soapRequestData.append("<ID>"+0+"</ID>");//核对日期
        soapRequestData.append("<Orderid>"+0+"</Orderid>");//核对日期
        soapRequestData.append("<Date>"+checkinData+"</Date>");//核对日期
        soapRequestData.append("<ClientNetPrice>"+ClientNetPrice+"</ClientNetPrice>");//核对日期
        soapRequestData.append("<NetPrice>"+NetPrice+"</NetPrice>");//核对日期
        soapRequestData.append("<AddBed>"+-1.0+"</AddBed>");//核对日期
        soapRequestData.append("<ServiceFee>"+0+"</ServiceFee>");//核对日期
        soapRequestData.append("<ServiceFee1>"+0+"</ServiceFee1>");//核对日期
        soapRequestData.append("<RaisePrice>"+0+"</RaisePrice>");//核对日期
        soapRequestData.append("<FavorablePrice>"+0+"</FavorablePrice>");//核对日期
        soapRequestData.append("<OtherCharge>"+0+"</OtherCharge>");//核对日期
        soapRequestData.append("<IsDeleted>"+false+"</IsDeleted>");//核对日期
        soapRequestData.append("<IsChanged>"+false+"</IsChanged>");//核对日期
        soapRequestData.append("</H_OrderFundDetail>");//核对日期


    }


    /**
     * -------------提交艺龙数据----------
     * @param soapRequestData
     * @return
     */
    private static void appendELCondition(StringBuilder soapRequestData,String phoneIp, boolean IsGuaranteeOrCharged, CardData card,
                                            String Hotelid,String RoomTypeId,String RatePlanCode,String PayType,String CheckInDate,
                                            String CheckOutDate,String CustomerType,int NumberOfUnits ,int GuestCount,String EarliestArrivalTime,
                                            String LaterArrivalTime,String CurrencyCode,double elTotalPrice,String TotalPrice,String ContactPersonName,
                                            String ContactEmail, List<NightlyRates> nightlyRates, List<B_TouristDOObj> enterPersons) {

        soapRequestData.append("<Action>"+"Create"+"</Action>");
      soapRequestData.append("<CreateCondition>");//-----
        soapRequestData.append("<CustomerIPAddress>"+phoneIp+"</CustomerIPAddress>");//客人访问IP
        soapRequestData.append("<IsGuaranteeOrCharged>"+IsGuaranteeOrCharged+"</IsGuaranteeOrCharged>");//如果是预付则为true 否则为false
        soapRequestData.append("<IsCreateOrderOnly>"+false+"</IsCreateOrderOnly>");//true - 表示本次请求只创建订单，不提供支付信息；订单创建成功后，请求者再通过

        soapRequestData.append("<IsForceGuarantee>"+card.isNeedRoule()+"</IsForceGuarantee>");//是否强制担保

        soapRequestData.append("<IsNeedInvoice>"+false+"</IsNeedInvoice>");//是否需要发票

        soapRequestData.append("<HotelId>"+Hotelid+"</HotelId>");
        soapRequestData.append("<RoomTypeId>"+RoomTypeId+"</RoomTypeId>");
        soapRequestData.append("<RatePlanId>"+RatePlanCode+"</RatePlanId>");
        soapRequestData.append("<PaymentType>"+PayType+"</PaymentType>");

        //------pass----13
        soapRequestData.append("<ArrivalDate>"+CheckInDate+"T00:00:00"+"</ArrivalDate>");
        soapRequestData.append("<DepartureDate>"+CheckOutDate+"T00:00:00"+"</DepartureDate>");
        soapRequestData.append("<CustomerType>"+CustomerType+"</CustomerType>");
        soapRequestData.append("<NumberOfRooms>"+NumberOfUnits+"</NumberOfRooms>");//
        soapRequestData.append("<NumberOfCustomers>"+GuestCount+"</NumberOfCustomers>");
        soapRequestData.append("<EarliestArrivalTime>"+EarliestArrivalTime+"</EarliestArrivalTime>");
        soapRequestData.append("<LatestArrivalTime>"+LaterArrivalTime+"</LatestArrivalTime>");
        soapRequestData.append("<CurrencyCode>"+CurrencyCode+"</CurrencyCode>");
        soapRequestData.append("<TotalPrice>"+elTotalPrice+"</TotalPrice>");
        soapRequestData.append("<ConfirmationType>"+"NotAllowedConfirm"+"</ConfirmationType>");
        soapRequestData.append("<NoteToHotel>"+"NoteToHotel"+"</NoteToHotel>");//给酒店备注
        soapRequestData.append("<NoteToElong>"+"东美在线订单"+"</NoteToElong>");//给艺龙备注


        //当预付产品使用结算价的时候，如果发票模式是酒店开发票，需要传入销售给客人的最终价格。传入的是人民币的值\\非人民币报价
        //的产品需要自行先根据艺龙汇率进行转换。
        soapRequestData.append("<CustomerPrice>"+TotalPrice+"</CustomerPrice>");

         soapRequestData.append("<OrderValidation>");
           appendOrderValidation(soapRequestData);
          soapRequestData.append("</OrderValidation>");
//            /***艺龙需要的联系人信息 pass，需要填写公司信息***/
        soapRequestData.append("<Contact>");
        soapRequestData.append("<Name>"+ContactPersonName+"</Name>");//登录人姓名
        soapRequestData.append("<Email>"+ContactEmail+"</Email>");//登录人邮箱
        soapRequestData.append("<Mobile>"+ R.string.companyMobil+"</Mobile>");
        soapRequestData.append("<Phone>"+R.string.companyphone+"</Phone>");
        soapRequestData.append("<Fax>"+R.string.fax+"</Fax>");//传真
        soapRequestData.append("<Gender>"+"Unknown"+"</Gender>");
        soapRequestData.append("</Contact>");

//        soapRequestData.append("<NightlyRates>");
//        for (int i = 0; i <nightlyRates.size() ; i++) {
//            NightlyRates rates = nightlyRates.get(i);
//            RatePlans ratePlan = MyApp.getPlan();
//            soapRequestData.append("<NightlyRate>");
//            soapRequestData.append("<Date>"+rates.getDate()+"</Date>");
//            if (ratePlan.getPaymentType().equals("Prepay")){
//                soapRequestData.append("<Rate>"+rates.getCost()+"</Rate>");
//            }else if (ratePlan.getPaymentType().equals("SelfPay")){
//                soapRequestData.append("<Rate>"+rates.getMember()+"</Rate>");
//            }else{
//                soapRequestData.append("<Rate>"+rates.getCost()+"</Rate>");
//            }
//            soapRequestData.append("</NightlyRate>");
//        }
//        soapRequestData.append("</NightlyRates>");

/**入住房间的旅客信息**/
        soapRequestData.append("<OrderRooms>");//这里是多个Customers   OrderRooms  OrderRoom
        for (int i = 0; i <NumberOfUnits ; i++) {
            soapRequestData.append("<CreateOrderRoom>");
            soapRequestData.append("<Customers>");
                B_TouristDOObj enterPerson = enterPersons.get(i);
                soapRequestData.append("<Customer>");
                soapRequestData.append("<Name>"+enterPerson.getTouristName()+"</Name>");
                soapRequestData.append("<Email>"+enterPerson.getAddress()+"</Email>");
                soapRequestData.append("<Mobile>"+enterPerson.getMobileNo()+"</Mobile>");
                soapRequestData.append("<Phone>"+enterPerson.getMobileNo()+"</Phone>");
                soapRequestData.append("<Fax>"+"0"+"</Fax>");
                soapRequestData.append("<Gender>"+"Unknown"+"</Gender>");
                soapRequestData.append("<Nationality>"+"All"+"</Nationality>");
                soapRequestData.append("</Customer>");
            soapRequestData.append("</Customers>");

            soapRequestData.append("</CreateOrderRoom>");
        }

        soapRequestData.append("</OrderRooms>");
/***发票信息为固定内容*/
        soapRequestData.append("<Invoice>");
        soapRequestData.append("<Title>"+"上海东美在线旅行社有限公司"+"</Title>");
        soapRequestData.append("<ItemName>"+"代订房费"+"</ItemName>");
        soapRequestData.append("<Amount>"+TotalPrice+"</Amount>");
        soapRequestData.append("<Recipient>");
        soapRequestData.append("<Province>"+"上海市"+"</Province>");
        soapRequestData.append("<City>"+"上海市"+"</City>");
        soapRequestData.append("<District>"+"徐汇区"+"</District>");
        soapRequestData.append("<Street>"+"肇嘉浜路376号轻工大厦3楼"+"</Street>");
        soapRequestData.append("<PostalCode>"+"200031"+"</PostalCode>");
        soapRequestData.append("<Name>"+"黄阳"+"</Name>");
        soapRequestData.append("<Phone>"+ R.string.companyphone+"</Phone>");
        soapRequestData.append("<Email>"+"hotelbooking@sky-trip.com"+"</Email>");
        soapRequestData.append("</Recipient>");
        soapRequestData.append("</Invoice>");
/***担保信用卡***/
        Log.e(TAG, "－－是否需要担保－－"+card.isNeedRoule() );
        if (card.isNeedRoule()){
            soapRequestData.append("<CreditCard>");
             appendCreditCard(soapRequestData,card);
            soapRequestData.append("</CreditCard>");
         }
//        soapRequestData.append("<MustVouchInRuleType/>");//强制担保类型
        soapRequestData.append("<IsNewPaymentFlow>"+false+"</IsNewPaymentFlow>");//新的付款流程
     soapRequestData.append("</CreateCondition>");
}

    /**
     * 插入OrderValidation
     * @param soapRequestData
     */
    private static void appendOrderValidation(StringBuilder soapRequestData) {
        soapRequestData.append("<Type>"+0+"</Type>");
        soapRequestData.append("<GuaranteeAmount>"+0+"</GuaranteeAmount>");
        soapRequestData.append("<CancelTime>"+"0001-01-01T00:00:00"+"</CancelTime>");
    }

    /**
     * 信用卡
     * @param soapRequestData
     * card
     * @return
     */
    private static void  appendCreditCard(StringBuilder soapRequestData,CardData card) {
        soapRequestData.append("<Number>"+card.getCardNumber()+"</Number>");
        soapRequestData.append("<CVV>"+card.getCardCVV()+"</CVV>");
        soapRequestData.append("<ExpirationYear>"+card.getYear()+"</ExpirationYear>");//有效期-年
        soapRequestData.append("<ExpirationMonth>"+card.getMonth()+"</ExpirationMonth>");
        soapRequestData.append("<HolderName>"+card.getHorderName()+"</HolderName>");
        soapRequestData.append("<IdType>"+card.getIDType()+"</IdType>");
        soapRequestData.append("<IdNo>"+card.getIDNumber()+"</IdNo>");

    }

    /**
     * 申请单
     * @param soapRequestData
     * @return
     */
    private static void  appendBAplay(StringBuilder soapRequestData,int Bookid , int DepId,String ContactEmail,String EarliestArrivalTime,String LaterArrivalTime) {
        soapRequestData.append("<ID>"+0+"</ID>");
        soapRequestData.append("<BookID>"+Bookid+"</BookID>");
        soapRequestData.append("<OrderID>"+0+"</OrderID>");
        soapRequestData.append("<DepID>"+DepId+"</DepID>");
        soapRequestData.append("<AuditID>"+0+"</AuditID>");//审核id
        soapRequestData.append("<AuditMail>"+ContactEmail+"</AuditMail>");//审核信箱
        soapRequestData.append("<Status>"+2+"</Status>");//审核状态 int 不需要为2 ，，0未审核  1 已审核
        soapRequestData.append("<insertDate>"+EarliestArrivalTime+"</insertDate>");//生成日期
        soapRequestData.append("<AuditDate>"+LaterArrivalTime+"</AuditDate>");//审核日期
        soapRequestData.append("<Reason>"+"酒店预订"+"</Reason>");//申请原因
        soapRequestData.append("<ApplyStatus>"+2+"</ApplyStatus>");//订单状态
        soapRequestData.append("<QueryDateStart>"+"0001-01-01T00:00:00"+"</QueryDateStart>");//订单状态
        soapRequestData.append("<QureryDateEnd>"+"0001-01-01T00:00:00"+"</QureryDateEnd>");//订单状态

    }

    /**
     * 旅客落本地
     * @param soapRequestData
     * @return
     */
    private static void appendHorderGuest(StringBuilder soapRequestData,B_TouristDOObj enterPerson) {
        soapRequestData.append("<HorderGuest>");
        soapRequestData.append("<Id>"+0+"</Id>");
        soapRequestData.append("<Orderid>"+0+"</Orderid>");
        soapRequestData.append("<Name>"+ enterPerson.getTouristName()+"</Name>");
        soapRequestData.append("<IdNumber>"+enterPerson.getIDNumber()+"</IdNumber>");
        soapRequestData.append("<IdType>"+enterPerson.getIDType()+"</IdType>");
        soapRequestData.append("<Mobile>"+enterPerson.getMobileNo()+"</Mobile>");
        soapRequestData.append("<IsDeleted>"+false+"</IsDeleted>");
        soapRequestData.append("<IsChanged>"+false+"</IsChanged>");
        soapRequestData.append("</HorderGuest>");

    }

    /**
     * 服务费用暂时全部无值
     * @param soapRequestData
     * @return
     */
    private static void appendFkHorderfuReferenceHorder(StringBuilder soapRequestData,String TotalPrice) {
        soapRequestData.append("<Orderid>"+0+"</Orderid>");
        soapRequestData.append("<Num>"+0+"</Num>");
        soapRequestData.append("<NetPrice>"+TotalPrice+"</NetPrice>");
        soapRequestData.append("<ClientNetPrice>"+TotalPrice+"</ClientNetPrice>");
        soapRequestData.append("<SaleAmount>"+TotalPrice+"</SaleAmount>");
        soapRequestData.append("<TakePrice>"+0+"</TakePrice>");
        soapRequestData.append("<RealPrice>"+0+"</RealPrice>");
        soapRequestData.append("<Tax>"+0+"</Tax>");//税
        soapRequestData.append("<Profit>"+0+"</Profit>");//毛利
        soapRequestData.append("<Award1>"+0+"</Award1>");//奖励1
        soapRequestData.append("<Award2>"+0+"</Award2>");
        soapRequestData.append("<RaisePrice>"+0+"</RaisePrice>");//提价
        soapRequestData.append("<ServiceFee>"+0+"</ServiceFee>");//服务费
        soapRequestData.append("<FavorablePrice>"+0+"</FavorablePrice>");//优惠
        soapRequestData.append("<OtherCharge>"+0+"</OtherCharge>");//其他费用
        soapRequestData.append("<CurrencyCode>"+"CNY"+"</CurrencyCode>");
        soapRequestData.append("<Memo>"+""+"</Memo>");
        soapRequestData.append("<IsDeleted>"+false+"</IsDeleted>");
        soapRequestData.append("<IsChanged>"+false+"</IsChanged>");

    }

    /**
     * 联系人信息落在本地
     * @param soapRequestData
     * @param ContactPersonName
     * @param ContactPhoneNumber
     * @param ContactEmail
     * @return
     */
    private static void appendHorderContact(StringBuilder soapRequestData,String ContactPersonName,String ContactPhoneNumber,String ContactEmail) {
        soapRequestData.append("<HorderContact>");
        soapRequestData.append("<Id>"+0+"</Id>");
        soapRequestData.append("<Orderid>"+0+"</Orderid>");
        soapRequestData.append("<ContactType>"+0+"</ContactType>");
        soapRequestData.append("<ContactPersonName>"+ContactPersonName+"</ContactPersonName>");
        soapRequestData.append("<PhoneNumber>"+ContactPhoneNumber+"</PhoneNumber>");
        soapRequestData.append("<PhoneTechType>"+0+"</PhoneTechType>");
        soapRequestData.append("<Email>"+ContactEmail+"</Email>");
        soapRequestData.append("<IsDeleted>"+false+"</IsDeleted>");
        soapRequestData.append("<IsChanged>"+false+"</IsChanged>");
        soapRequestData.append("<ConfirmType>"+0+"</ConfirmType>");
        soapRequestData.append("</HorderContact>");
    }



    /**
     * 酒店数据验证
     * @return
     */
    public static HotelDataValidateResult hotelDataValidata(String ArrivalDate, String DepartureDate, String EarliestArrivalTime, String LatestArrivalTime, String HotelId,
                                                            String RoomTypeId, String RatePlanId, double TotalPrice, int NumberOfRooms){
        PostMethod postMethod = new PostMethod(WebServUtil.HOTEL_RELAY_API);
        try{
            byte b [] = builderHotelData(ArrivalDate,DepartureDate,EarliestArrivalTime,LatestArrivalTime,HotelId,RoomTypeId,RatePlanId,TotalPrice,NumberOfRooms).getBytes(ENCODE);
//              Log.e(TAG, "hotelDataValidata: 提交参数："+builderHotelData(ArrivalDate,DepartureDate,EarliestArrivalTime,LatestArrivalTime,HotelId,RoomTypeId,RatePlanId,TotalPrice,NumberOfRooms));
            InputStream is = new ByteArrayInputStream(b, 0, b.length);
            RequestEntity re = new InputStreamRequestEntity(is, b.length,
                    CONTENT_TYPE);
            postMethod.setRequestEntity(re);
            HttpClient httpClient = new HttpClient();
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == WebServUtil.HTTP_STATUS_OK) {
                String response = postMethod.getResponseBodyAsString();
                return parseDataValidata(response) ;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "hotelDataValidata: "+e.getMessage() );
        }
        return null;
    }

    /**
     * 解析验证酒店数据
     * @param response
     * @return
     */
    private static HotelDataValidateResult parseDataValidata(String response) {
        HotelDataValidateResult hotelDataValidata = new HotelDataValidateResult();
        Element root;
        Document doc;
        SAXReader reader = new SAXReader();
        try {
            doc = reader.read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            root = doc.getRootElement();
            Element obj = root.element("Body")
                    .element("HotelDataValidateResponse")
                    .element("HotelDataValidateResult");
            hotelDataValidata.setSuccess(parseBoolean(obj.elementTextTrim("Success")));
            hotelDataValidata.setMessage(obj.elementTextTrim("Message"));
            hotelDataValidata.setElapsedMilliseconds(parseLong(obj.elementTextTrim("ElapsedMilliseconds")));
            ResultObject reObj = new ResultObject();
            Element ResObject =obj.element("ResultObject");
            reObj.setCode(ResObject.elementTextTrim("Code"));
            if (null !=ResObject.element("Result")){
                Element resO = ResObject.element("Result");

                if (null!=resO.elementTextTrim("ResultCode")){
                    reObj.setResultCode(resO.elementTextTrim("ResultCode"));
                }
                if (null !=resO.elementTextTrim("CurrencyCode")){
                    reObj.setCurrencyCode(resO.elementTextTrim("CurrencyCode"));
                }
                if (null !=resO.elementTextTrim("CancelTime")){
                    reObj.setCancelTime(resO.elementTextTrim("CancelTime"));
                }
                if (null !=resO.elementTextTrim("GuaranteeRate")){
                    reObj.setGuaranteeRate(resO.elementTextTrim("GuaranteeRate"));
                }
            }


            hotelDataValidata.setResultObject(reObj);
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "parseDataValidata--err: "+e.getMessage() );
        }

        return hotelDataValidata;
    }

    /**
     * 预订酒店数据验证参数
     * @param ArrivalDate
     * @param DepartureDate
     * @param EarliestArrivalTime
     * @param LatestArrivalTime
     * @param HotelId
     * @param RoomTypeId
     * @param RatePlanId
     * @param TotalPrice
     * @param NumberOfRooms
     * @return
     */
    private static  String builderHotelData(String ArrivalDate,String DepartureDate,String EarliestArrivalTime,String LatestArrivalTime,String HotelId,
                                            String RoomTypeId,String RatePlanId,double TotalPrice,int NumberOfRooms){
        StringBuilder  soapRequestData = buildSoapHeader();
        soapRequestData.append("<"+WebServUtil.HOTEL_DATA_VALIDATA+" xmlns=\""+ WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>" + WebServUtil.HBE_USER_NAME + "</UserID>");
        soapRequestData.append("<Password>" +WebServUtil.HBE_USER_PAW+ "</Password>");
        soapRequestData.append("<Patameter>");
        soapRequestData.append("<Action>"+"Validate"+"</Action>");
        soapRequestData.append("<ValidateCondition>");
        soapRequestData.append("<ArrivalDate>"+ArrivalDate+"</ArrivalDate>");
        soapRequestData.append("<DepartureDate>"+DepartureDate+"</DepartureDate>");
        soapRequestData.append("<EarliestArrivalTime>"+EarliestArrivalTime+"</EarliestArrivalTime>");
        soapRequestData.append("<LatestArrivalTime>"+LatestArrivalTime+"</LatestArrivalTime>");
        soapRequestData.append("<HotelId>"+HotelId+"</HotelId>");
        soapRequestData.append("<RoomTypeId>"+RoomTypeId+"</RoomTypeId>");
        soapRequestData.append("<RatePlanId>"+RatePlanId+"</RatePlanId>");
        soapRequestData.append("<TotalPrice>"+TotalPrice+"</TotalPrice>");
        soapRequestData.append("<NumberOfRooms>"+NumberOfRooms+"</NumberOfRooms>");
        soapRequestData.append("</ValidateCondition>");
        soapRequestData.append("</Patameter>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + WebServUtil.HOTEL_DATA_VALIDATA + ">");
        return buildSoapFooter(soapRequestData).toString();
    }


}
