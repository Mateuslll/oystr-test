# **Sistema de Web Scraping para Máquinas Agrícolas**

Este projeto é um sistema de web scraping em Java que coleta dados de sites de máquinas agrícolas, extrai informações relevantes e as organiza em um formato estruturado. O sistema utiliza **JSoup** para análise e raspagem de HTML, **JUnit** para testes e suporta scraping modular para diferentes sites. Ele é projetado para capturar detalhes como modelo, preço, tipo de contrato, marca, ano, horas trabalhadas, cidade e URLs de fotos das máquinas.

## **Tecnologias Utilizadas**

- **Java 17**
- **JSoup** para análise e raspagem de HTML
- **JUnit** para testes unitários
- **Maven** para gerenciamento de dependências
- **Git** para controle de versão

## **Processo de Web Scraping**

O sistema realiza a raspagem de dados de máquinas em diversos sites de vendas de máquinas agrícolas. O processo segue os seguintes passos:

1. **Enviar Requisição HTTP**: O sistema envia uma requisição HTTP para a URL alvo a fim de obter o conteúdo da página.
2. **Analisar o HTML**: O JSoup é utilizado para analisar o HTML e extrair dados específicos, como modelo da máquina, preço e outros detalhes.
3. **Processar os Dados**: Os dados extraídos são processados e mapeados para um objeto Machine.
4. **Retornar Dados Estruturados**: O sistema retorna os dados estruturados, prontos para processamento adicional ou armazenamento.

### **Principais Atributos Extraídos**

- **Modelo**: Marca e modelo da máquina.
- **Preço**: Valor de venda.
- **Tipo de Contrato**: Tipo de contrato (ex.: venda, aluguel).
- **Marca**: Marca da máquina.
- **Ano**: Ano de fabricação.
- **Horas Trabalhadas**: Total de horas trabalhadas pela máquina.
- **Cidade**: Localização (cidade/estado) onde a máquina está.
- **URL da Foto**: URL da foto da máquina.

## **Sites Suportados**

O sistema suporta a raspagem de dados dos seguintes sites de máquinas agrícolas:

- **Agrofy**
- **TratoresBot**
- **Mercado Maquinas**

Cada bot é implementado com lógica específica para o formato de cada site.

## **Estrutura do Projeto**

![image](https://github.com/user-attachments/assets/4c27e389-40b9-4d95-bb0e-5194459a1756)


## **Configuração do Projeto**

### **Pré-requisitos**

1. **Java 8**: Certifique-se de que o Java 8 está instalado e configurado.
2. **Maven**: Verifique se o Maven está instalado para gerenciar as dependências.
3. **Git**: Utilize o Git para controlar as versões e mudanças no projeto.

### **Passos para Instalação**

- Clone o repositório:  

```xml

git clone <https://github.com/seu-repo/web-scraping-maquinas.git>

```

Navegue até o diretório do projeto:  

```xml

cd web-scraping-maquinas

```

Compile o projeto utilizando o Maven:  

   
```xml

mvn clean install

```

### **Executando o Processo de Scraping**

1. Modifique as URLs nos arquivos dos bots (AgrofyBot, TratoresBot, MercadoMaquinasBot) para os anúncios desejados.

- Execute a aplicação ou os testes:  
 
```xml

- mvn test

```

## **Testes Unitários**

O projeto inclui testes unitários para cada bot, a fim de garantir que os dados estão sendo extraídos corretamente. São utilizados dados mockados para simular a estrutura do HTML e testar o processo de extração de dados. 

- Exemplo:

```xml

@Test

public void testFetchMachineMock() {

Machine mockMachine = AgrofyBotMock.buildMockMachine();

assertEquals("Agrofy Tractor X200", mockMachine.getModel());

assertEquals(55000.00, mockMachine.getPrice());

assertEquals("Venda", mockMachine.getContractType());

assertEquals("Agrofy Brand", mockMachine.getMake());

assertEquals(2022, mockMachine.getYear());

assertEquals(1200, mockMachine.getWorkedHours());

assertEquals("São Paulo / SP", mockMachine.getCity());

assertEquals("<https://example.com/photo.jpg>", mockMachine.getPhotoUrl());

}
```

## **Contribuições**

Sinta-se à vontade para fazer um fork deste repositório, abrir issues ou enviar pull requests para melhorar o projeto. Contribuições são sempre bem-vindas!

## **Melhorias Futuras**

- Adicionar suporte para scraping de novos sites.
- Implementar tratamento de erros e mecanismos de repetição para tornar a raspagem mais robusta.
- Adicionar funcionalidade para salvar os dados extraídos em um banco de dados.
- Ampliar a cobertura de testes com casos mais completos.

_"E tudo o que pedirem em oração, se crerem, vocês receberão". — Mateus 21:22_
