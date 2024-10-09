package steps;

import configuration.Constants;
import interactions.SigipImpCauInteractions;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.text.ParseException;

import static functions.Functions.loadYAMLData;

public class SigipImpCauSteps extends SigipImpCauInteractions {

    @Dado("que eu seleciono o XML {string} e o YAML {string}")
    public void selecionarXmlEYaml(String xml, String yaml) {
        SelectGetXMLInformation(Constants.PATH_XML + xml + Constants.ExtensionXML);
        SelectandGetYAMLInformations(Constants.PATH_YAML + yaml + Constants.ExtensionYAML);
    }

    @Dado("realize a substituicao dos dados primarios do arquivo")
    public void realize_a_substituicao_dos_dados_primarios_do_arquivo() throws ParseException {
        ReplaceValuesInXML();
    }

    @Quando("enviar o request via REST")
    public void enviar_o_request_via_rest() {
        PostXML();
    }

    @Entao("validar o response {string}")
    public void validar_o_response(String typeResponse) {

    }
}
