package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;

public class PessoaJuridicaRepo {

    private ArrayList<PessoaJuridica> pessoasJuridicas = new ArrayList<>();

    public PessoaJuridicaRepo() {
        this.pessoasJuridicas = new ArrayList<>();
    }

    public void inserir(PessoaJuridica pessoaJuridica) {
        pessoasJuridicas.add(pessoaJuridica);
    }

    public void alterar(PessoaJuridica pessoaAlterada) {
        for (int i = 0; i < pessoasJuridicas.size(); i++) {
            PessoaJuridica pessoaExistente = pessoasJuridicas.get(i);
            if (pessoaExistente.getId() == pessoaAlterada.getId()) {
                pessoaExistente.setNome(pessoaAlterada.getNome());
                pessoaExistente.setCnpj(pessoaAlterada.getCnpj());
            }
        }

    }

    public boolean excluir(int id) {
        return pessoasJuridicas.removeIf(pessoa -> id == pessoa.getId());
    }

    public Optional<PessoaJuridica> obter(int id) {
        return pessoasJuridicas.stream().filter(pessoa -> id == pessoa.getId()).findFirst();
    }

    public ArrayList<PessoaJuridica> obterTodos() {
        return new ArrayList<>(pessoasJuridicas);
    }

    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(pessoasJuridicas);
        }
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            pessoasJuridicas = (ArrayList<PessoaJuridica>) ois.readObject();
        }
    }
}
