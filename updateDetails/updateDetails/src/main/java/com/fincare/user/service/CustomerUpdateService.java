package com.fincare.user.service;

import com.fincare.user.entity.CustomerUpdateProxy;
import com.fincare.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerUpdateService {

    @Autowired
    private CustomerUpdateProxy customerUpdateProxy;

    public Map<String,String> getDetails(UserEntity userEntity)
    {
        String reqXml="<input Operation=\"customerUpdate(SessionContext,String)\">\n" +
                "  <SessionContext>\n" +
                "    <BranchCode>10032</BranchCode>\n" +
                "    <Channel>IEXCEED</Channel>\n" +
                "    <ExternalReferenceNo>2017122917071234</ExternalReferenceNo>\n" +
                "    <SupervisorContext>\n" +
                "      <UserId>IEXCEED</UserId>\n" +
                "      <PrimaryPassword>V2VsY29tZUAxMjM=</PrimaryPassword>\n" +
                "    </SupervisorContext>\n" +
                "  </SessionContext>\n" +
                "  <Action>CUSTUPD</Action>\n" +
                "  <Data>>CIF.ACN=|CIF.ZMPH=9538953404|CIF.ZADHREFNO="+userEntity.getAadharNumber()+"|CIF.ACN="+userEntity.getAccountNumber()+"|CIF.OIT=19|CIF.MAR="+userEntity.getmStatus()+"</Data>\n" +
                "</input>   ";

//        String reqXml="<input Operation=\"customerUpdate(SessionContext,String)\">\n" +
//                "\n" +
//                "  <SessionContext>\n" +
//                "\n" +
//                "    <BranchCode>10032</BranchCode>\n" +
//                "\n" +
//                "    <Channel>IEXCEED</Channel>\n" +
//                "\n" +
//                "    <ExternalReferenceNo>3456787654321456789</ExternalReferenceNo>\n" +
//                "\n" +
//                "    <SupervisorContext>\n" +
//                "\n" +
//                "      <UserId>IEXCEED</UserId>\n" +
//                "\n" +
//                "      <PrimaryPassword>V2VsY29tZUAxMjM=</PrimaryPassword>\n" +
//                "\n" +
//                "    </SupervisorContext>\n" +
//                "\n" +
//                "  </SessionContext>\n" +
//                "\n" +
//                "  <Action>CUSTUPD</Action>\n" +
//                "\n" +
//                "  <Data>CIF.ACN="+userEntity.getAccountNumber()+"|CIF.ZADHREFNO="+userEntity.getAadharNumber()+"|</Data>\n" +
//                "\n" +
//                "</input>  ";

        String resXml=customerUpdateProxy.updateDetails(reqXml);
        Map<String,String> map=new HashMap<>();

        InputStream inputStream=new ByteArrayInputStream(resXml.getBytes(StandardCharsets.UTF_8));
        try{

            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document document=documentBuilder.parse(inputStream);

           // String result=document.getElementsByTagName("TransactionStatus").item(0).getTextContent();
            //System.out.println(result);
            if(document.getElementsByTagName("ErrorCode")!=null &&
            document.getElementsByTagName("ErrorCode").item(0).getTextContent().equals("0"))
            {
                map.put("ReplyText",document.getElementsByTagName("ReplyText").item(0).getTextContent());
            }
            else
            {
                map.put("ErrorMessage",document.getElementsByTagName("ErrorMessage").item(0).getTextContent());
            }

        }
       catch (Exception e)
       {
            System.out.println(e.getMessage());
       }

    return map;
    }
}
