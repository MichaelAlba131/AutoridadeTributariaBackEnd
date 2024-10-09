#language: pt
#encoding: UTF-8

Funcionalidade: Envio de Response XML do serviço SIGIPIMPCAU
  Designada a montar o XML de acordo com as massas selecionadas
  Envio do request XML e validaçao do response

  @test
  Esquema do Cenario: [Modulo] - "<cenario>"
    Dado que eu seleciono o XML "<xml>" e o YAML "<yaml>"
    E realize a substituicao dos dados primarios do arquivo
    Quando enviar o request via REST
    Entao validar o response "<response>"

    Exemplos:
      | cenario   | xml     | yaml      | response |
      | Auto00001 | xmlBase | auto00001 | XML1     |


