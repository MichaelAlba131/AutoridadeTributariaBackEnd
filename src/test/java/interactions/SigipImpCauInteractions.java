package interactions;

import api.RestAssuredDefinitions;
import api.SigipImpCauURLs.*;
import lombok.Getter;
import lombok.Setter;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import static functions.Functions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SigipImpCauInteractions {

    @Getter
    @Setter
    public static String replaceXML;

    public static void SelectGetXMLInformation(String xml) {
        loadXMLFile(xml);
    }

    public static void SelectandGetYAMLInformations(String yaml) {
        loadYAMLData(yaml);
        String addInformationCode = getValue("SigipimpcauRequest>request>AdditionalInformation>Code");
    }

    public static void ReplaceValuesInXML() throws ParseException {
        setReplaceXML(null);
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String modificationDate = getValue("SigipimpcauRequest>request>ModificationDate");
        Date date = inputFormat.parse(modificationDate);
        String formattedModificationDate = outputFormat.format(date);
        String dateYAML = getValue("SigipimpcauRequest>request>Date");
        date = inputFormat.parse(dateYAML);
        String formattedDate = outputFormat.format(date);
        String replaceXMLItem = getXmlData().toString()
                .replace("$schVersionNumber$", getValue("SigipimpcauRequest>request>VersionNumber"))
                .replace("$schModificationDate$", formattedModificationDate)
                .replace("$schLocalReferenceNumber$", getValue("SigipimpcauRequest>request>LocalReferenceNumber"))
                .replace("$schTemporaryNumber$", getValue("SigipimpcauRequest>request>TemporaryNumber"))
                .replace("$schDeclarationNumber$", getValue("SigipimpcauRequest>request>DeclarationNumber"))
                .replace("$schDate$", formattedDate)
                .replace("$schH6Flag$", getValue("SigipimpcauRequest>request>H6Flag"))
                .replace("$schDeclarationCustomsOffice$", getValue("SigipimpcauRequest>request>DeclarationCustomsOffice"))
                .replace("$schGoodsPresentationCustomsOffice$", getValue("SigipimpcauRequest>request>GoodsPresentationCustomsOffice"))
                .replace("$schGrossMass$", getValue("SigipimpcauRequest>request>GrossMass"))
                .replace("$schAdditionalInformationCode$", getValue("SigipimpcauRequest>request>AdditionalInformation>Code"))
                .replace("$schAdditionalInformationText$", getValue("SigipimpcauRequest>request>AdditionalInformation>Text"))
                .replace("$schAdditionalReferenceType$", getValue("SigipimpcauRequest>request>AdditionalReference>Type"))
                .replace("$schAdditionalReferenceReferenceNumber$", getValue("SigipimpcauRequest>request>AdditionalReference>ReferenceNumber"))
                .replace("$schTypeTD1$", getValue("SigipimpcauRequest>request>TransportDocument1>Type"))
                .replace("$schReferenceNumberTD1$", getValue("SigipimpcauRequest>request>TransportDocument1>ReferenceNumber"))
                .replace("$schTypeTD2$", getValue("SigipimpcauRequest>request>TransportDocument2>Type"))
                .replace("$schReferenceNumberTD2$", getValue("SigipimpcauRequest>request>TransportDocument2>ReferenceNumber"))
                .replace("$schSimplifiedAuthorizationFlag$", getValue("SigipimpcauRequest>request>SimplifiedAuthorizationFlag"))
                .replace("$schDestinationCountry$", getValue("SigipimpcauRequest>request>DestinationCountry"))
                .replace("$schDispatchingCountry$", getValue("SigipimpcauRequest>request>DispatchingCountry"))
                .replace("$schRegionalCode$", getValue("SigipimpcauRequest>request>RegionalCode"))
                .replace("$schSystemId$", getValue("SigipimpcauRequest>request>SystemId"))
                .replace("$schDeclUser$", getValue("SigipimpcauRequest>request>DeclUser"))
                .replace("$schAdditionsAdditionNumber$", getValue("SigipimpcauRequest>request>Additions>AdditionNumber"))
                .replace("$schAdditionsSuplementaryUnit$", getValue("SigipimpcauRequest>request>Additions>SuplementaryUnit"))
                .replace("$schAdditionsGrossMass$", getValue("SigipimpcauRequest>request>Additions>GrossMass"))
                .replace("$schAdditionsNetMass$", getValue("SigipimpcauRequest>request>Additions>NetMass"))
                .replace("$schAdditionsCusCode$", getValue("SigipimpcauRequest>request>Additions>CusCode"))
                .replace("$schAdditionsGoodsCodeSH$", getValue("SigipimpcauRequest>request>Additions>GoodsCodeSH"))
                .replace("$schAdditionsGoodsCodeNC$", getValue("SigipimpcauRequest>request>Additions>GoodsCodeNC"))
                .replace("$schAdditionsGoodsCodeTARIC$", getValue("SigipimpcauRequest>request>Additions>GoodsCodeTARIC"))
                .replace("$schAdditionsOriginCountryCode$", getValue("SigipimpcauRequest>request>Additions>OriginCountryCode"))
                .replace("$schAdditionsPreferredOriginCountry$", getValue("SigipimpcauRequest>request>Additions>PreferredOriginCountry"))
                .replace("$schAdditionsDestinationCountry$", getValue("SigipimpcauRequest>request>Additions>DestinationCountry"))
                .replace("$schAdditionsPreference$", getValue("SigipimpcauRequest>request>Additions>Preference"))
                .replace("$schAdditionsRequestedRegime$", getValue("SigipimpcauRequest>request>Additions>RequestedRegime"))
                .replace("$schAdditionsAdditionalRegime$", getValue("SigipimpcauRequest>request>Additions>AdditionalRegime"))
                .replace("$schAdditionsStatisticalValue$", getValue("SigipimpcauRequest>request>Additions>StatisticalValue"))
                .replace("$schAdditionsValuationMethod$", getValue("SigipimpcauRequest>request>Additions>ValuationMethod"))
                .replace("$schAdditionsMeasureUnitAndQualifier$", getValue("SigipimpcauRequest>request>Additions>TaxBase>MeasureUnitAndQualifier"))
                .replace("$schAdditionsQuantity$", getValue("SigipimpcauRequest>request>Additions>TaxBase>Quantity"))
                .replace("$schAdditionsVatRegime$", getValue("SigipimpcauRequest>request>Additions>VatRegime"))
                .replace("$schAdditionsCode$", getValue("SigipimpcauRequest>request>Additions>AdditionalInformation>Code"))
                .replace("$schAdditionsText$", getValue("SigipimpcauRequest>request>Additions>AdditionalInformation>Text"))
                .replace("$schAdditionsType$", getValue("SigipimpcauRequest>request>Additions>SupportDocument>Type"))
                .replace("$schAdditionsReferenceNumber$", getValue("SigipimpcauRequest>request>Additions>SupportDocument>ReferenceNumber"))
                .replace("$schAdditionsMeasureUnitAndQualifier2$", getValue("SigipimpcauRequest>request>Additions>SupportDocument>MeasureUnitAndQualifier"))
                .replace("$schAdditionsType2$", getValue("SigipimpcauRequest>request>Additions>AdditionalReference>Type"))
                .replace("$schAdditionsReferenceNumber2$", getValue("SigipimpcauRequest>request>Additions>AdditionalReference>ReferenceNumber"))
                .replace("$schAdditionsSimplifiedAuthorizationFlag$", getValue("SigipimpcauRequest>request>Additions>SimplifiedAuthorizationFlag"))
                .replace("$schAdditionsNationalAdditionalCode$", getValue("SigipimpcauRequest>request>Additions>NationalAdditionalCode"))
                .replace("$schAdditionsCustomsValue$", getValue("SigipimpcauRequest>request>Additions>CustomsValue"))
                .replace("$schAdditionsGoodsCode$", getValue("SigipimpcauRequest>request>Additions>PotentialDebt>GoodsCode"))
                .replace("$schAdditionsOriginCountryCode$", getValue("SigipimpcauRequest>request>Additions>PotentialDebt>OriginCountryCode"))
                .replace("$schAdditionsAdditionalCodeTARIC$", getValue("SigipimpcauRequest>request>Additions>PotentialDebt>AdditionalCodeTARIC"))
                .replace("$schAdditionsNationalAdditionalCode$", getValue("SigipimpcauRequest>request>Additions>PotentialDebt>NationalAdditionalCode"))
                .replace("$schAdditionsPreference2$", getValue("SigipimpcauRequest>request>Additions>PotentialDebt>Preference"))
                .replace("$schAdditionsRequestedRegime2$", getValue("SigipimpcauRequest>request>Additions>PotentialDebt>RequestedRegime"))
                .replace("$schAdditionsMeasureUnitAndQualifier3$", getValue("SigipimpcauRequest>request>Additions>PotentialDebt>UnitsForPD>MeasureUnitAndQualifier"))
                .replace("$schAdditionsQuantity2$", getValue("SigipimpcauRequest>request>Additions>PotentialDebt>UnitsForPD>Quantity"))
                .replace("$schAdditionsCustomsValue$", getValue("SigipimpcauRequest>request>Additions>PotentialDebt>CustomsValue"));

        if (getSnakeData().toString().contains("_C")) {
            Pattern pattern = Pattern.compile("\\b\\w*_C\\w*\\b");
            Matcher matcher = pattern.matcher(getSnakeData().toString());
            String lastItem = null;
            while (matcher.find()) {
                String itemFind = matcher.group();
                int findC = itemFind.indexOf("_C");
                itemFind = itemFind.substring(0, findC);
                lastItem = itemFind;
                String xmlValInitial = "<sch:" + itemFind + ">";
                String xmlValFinal = "</sch:" + itemFind + ">";
                int indexStart = replaceXMLItem.indexOf(xmlValInitial);
                int indexEnd = replaceXMLItem.indexOf(xmlValFinal);
                String contextItem = replaceXMLItem.substring(indexStart, indexEnd + xmlValFinal.length());
                //replaceXMLItem.replace(contextItem, )

                String txt = "";

            }
        }


        setReplaceXML(replaceXMLItem);
    }

    public static void PostXML() {
        RestAssuredDefinitions.RestPost(URLs.HOME.getURLName(), getReplaceXML());
    }

}
