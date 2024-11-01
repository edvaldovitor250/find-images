<body>
    <h1>📄 Documentação do Projeto</h1>
    <p>Esta documentação descreve o funcionamento de uma aplicação que processa imagens e exibe resultados usando Thymeleaf, Spring e HTML.</p>
    <div class="section">
        <h2>📝 Funções e Componentes Principais</h2>
        <p>A aplicação é composta por:</p>
        <ul>
            <li>🌐 Controlador em Spring Boot para processar requisições e enviar dados para o template.</li>
            <li>📄 Template HTML Thymeleaf para exibir os resultados de imagem.</li>
            <li>🔍 Serviço para coleta de URLs de imagens.</li>
        </ul>
    </div>
    <div class="section">
        <h2>📦 Controlador</h2>
        <p>O controlador no Spring Boot é responsável por processar a requisição e enviar os dados para o template <code>imageResults.html</code>.</p>
        <h3>Exemplo de código:</h3>
        <div class="code-snippet">
            <code>
@GetMapping("/imageResults")
public String getImageResults(Model model) {
    List&lt;ImageUrl&gt; images = imageScraperService.scrapeImages("URL_AQUI");
    model.addAttribute("images", images);
    return "imageResults";
}
            </code>
        </div>
        <p><strong>Explicação:</strong></p>
        <ul>
            <li>🛠️ O método <code>getImageResults</code> processa a rota <code>/imageResults</code>.</li>
            <li>🔄 <code>imageScraperService.scrapeImages</code> coleta URLs de imagens e as armazena em uma lista.</li>
            <li>📤 A lista de URLs é adicionada ao modelo com o atributo <code>images</code>, que é enviado ao template.</li>
        </ul>
    </div>
    <div class="section">
        <h2>📑 Template Thymeleaf (<code>imageResults.html</code>)</h2>
        <p>O template exibe as imagens coletadas. Ele usa Thymeleaf para iterar sobre os resultados e exibir as URLs das imagens.</p>
        <h3>Exemplo de código:</h3>
        <div class="code-snippet">
            <code>
&lt;!DOCTYPE html&gt;
&lt;html xmlns:th="http://www.thymeleaf.org"&gt;
&lt;head&gt;
    &lt;meta charset="UTF-8"&gt;
    &lt;title&gt;Resultados das Imagens&lt;/title&gt;
&lt;/head&gt;
&lt;body&gt;
    &lt;h1&gt;Imagens encontradas&lt;/h1&gt;
    &lt;div th:if="${images}"&gt;
        &lt;ul&gt;
            &lt;li th:each="image : ${images}"&gt;
                &lt;img th:src="${image.url}" alt="Imagem" /&gt;
            &lt;/li&gt;
        &lt;/ul&gt;
    &lt;/div&gt;
    &lt;div th:if="${#lists.isEmpty(images)}"&gt;
        &lt;p&gt;Nenhuma imagem encontrada.&lt;/p&gt;
    &lt;/div&gt;
    &lt;a href="/"&gt;Voltar&lt;/a&gt;
&lt;/body&gt;
&lt;/html&gt;
            </code>
        </div>
        <p><strong>Explicação:</strong></p>
        <ul>
            <li>🔄 <code>th:each="image : ${images}"</code>: Itera pela lista <code>images</code> e exibe cada imagem.</li>
            <li>📷 <code>th:src="${image.url}"</code>: Define a URL da imagem, extraindo-a de <code>image.url</code>.</li>
            <li>❗ <code>th:if="${#lists.isEmpty(images)}"</code>: Exibe uma mensagem se não houver imagens na lista.</li>
        </ul>
    </div>
    <div class="section">
        <h2>🧩 Classe <code>ImageUrl</code></h2>
        <p>A classe <code>ImageUrl</code> representa uma imagem com uma URL e pode ser estruturada como segue:</p>
        <div class="code-snippet">
            <code>
public class ImageUrl {
    private String url;
    public ImageUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
            </code>
        </div>
        <p><strong>Explicação:</strong></p>
        <ul>
            <li>📥 Construtor <code>ImageUrl(String url)</code>: Inicializa o objeto com uma URL.</li>
            <li>🔗 <code>getUrl()</code>: Retorna a URL armazenada no objeto.</li>
            <li>🔧 <code>setUrl(String url)</code>: Atualiza a URL do objeto.</li>
        </ul>
    </div>
    <div class="section">
        <h2>📷 Imagens de Exemplo</h2>
        <div class="image-container">
    <h3>Imagem 1: Tela Inicial com Pesquisa e Erro</h3>
    <img src="https://github.com/user-attachments/assets/6cbc615a-02ff-4530-9ba2-4da00e20820d" alt="Tela Inicial com Pesquisa e Erro" />
</div>
<div class="image-container">
    <h3>Imagem 2: Exemplo de URLs das Imagens Coletadas</h3>
    <img src="https://github.com/user-attachments/assets/aeef7b45-af9d-4684-96ec-e2eada569602" alt="Exemplo de URLs das Imagens Coletadas" />
</div>
        <div class="image-container">
            <h3>Imagem 3: Erro de Exemplo</h3>
            <img src="https://github.com/user-attachments/assets/35e4594b-07b8-4067-b02f-a8e079f28c56" alt="Erro de exemplo" />
        </div>
    </div>
    <div class="section">
        <h2>💡 Tratamento de Erros</h2>
        <p>Erros podem ocorrer se a lista <code>images</code> estiver vazia ou se a propriedade <code>url</code> não existir no objeto. Para prevenir, podemos verificar e tratar listas vazias antes de enviar ao template:</p>
        <h3>Exemplo de código:</h3>
        <div class="code-snippet">
            <code>
List&lt;ImageUrl&gt; images = imageScraperService.scrapeImages("URL_AQUI");
if (images == null || images.isEmpty()) {
    model.addAttribute("images", Collections.emptyList());
}
            </code>
        </div>       
        <p><strong>Explicação:</strong> Se a lista estiver vazia ou nula, o modelo é preenchido com uma lista vazia.</p>
    </div>
    <div class="section">
        <h2>🚀 Como Rodar a Aplicação</h2>
        <ol>
            <li><strong>Clone o repositório:</strong> Em seu terminal, execute o comando:
                <pre><code>git clone https://github.com/edvaldovitor250/find-images.git</code></pre>
            </li>
            <li><strong>Entre na pasta do projeto:</strong>
                <pre><code>cd nome-do-repositorio</code></pre>
            </li>
            <li><strong>Compile e execute o projeto com Maven:</strong> Verifique se o Maven está instalado e rode:
                <pre><code>mvn spring-boot:run</code></pre>
            </li>
            <li><strong>Acesse a aplicação:</strong> Abra seu navegador e vá para:
                <pre><code>http://localhost:8080</code></pre>
            </li>
        </ol>
    </div>
    <h2 id="tech-stack-utilizada">Tech Stack Utilizada 🛠️</h2>
    <table align="center" width="1000px">
        <thead>
            <tr>
                <th><img src="https://skillicons.dev/icons?i=spring" width="100px" height="100px" alt="Spring Boot"/></th>
                <th><img src="https://skillicons.dev/icons?i=java" width="100px" height="100px" alt="Java"/></th>
            </tr>
        </thead>
        <tbody align="center">
            <tr>
                <td>Spring Boot</td>
                <td>Java</td>
            </tr>
            <tr>
                <td>🔖 3.0.0</td>
                <td>⚙️ 17</td>
            </tr>
        </tbody>
    </table>
    <h2 align="center">💻 Desenvolvedor</h2> 
<div align="center">
    <a href="https://github.com/edvaldovitor250">
        <img src="https://github.com/edvaldovitor250.png" width="170">
        <br>
        <sub>Edvaldo Vitor</sub>
    </a>
</div>
</body>
