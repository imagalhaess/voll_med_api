package med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.uf = dados.uf();
        this.cidade = dados.cidade();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
    }

    public void atualizarEndereco(DadosEndereco dados) {
        if (this.logradouro != null){
            this.logradouro = dados.logradouro();
        }
        if (this.bairro != null) {
            this.bairro = dados.bairro();
        }
        if (this.cep != null) {
            this.cep = dados.cep();
        }
        if (this.uf != null) {
            this.uf = dados.uf();
        }
        if (this.cidade != null) {
            this.cidade = dados.cidade();
        }
        if (this.numero != null) {
            this.numero = dados.numero();
        }
        if (this.complemento != null) {
            this.complemento = dados.complemento();
        }
    }
}
