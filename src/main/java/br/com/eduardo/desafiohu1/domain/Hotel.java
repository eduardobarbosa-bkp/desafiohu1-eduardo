package br.com.eduardo.desafiohu1.domain;

/**
 * @author: eduardo.barbosa
 * @since: 30/09/2015
 */
public class Hotel {

    private String id;

    private String cidade;

    private String nome;

    public Hotel(String id, String cidade, String nome) {
        this.id = id;
        this.cidade = cidade;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;

        Hotel hotel = (Hotel) o;

        return id.equals(hotel.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
