package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;

public class PessoaFisicaRepo {

    private ArrayList<PessoaFisica> pessoasFisicas = new ArrayList<>();

    public PessoaFisicaRepo() {
        this.pessoasFisicas = new ArrayList<>();
    }

    public void inserir(PessoaFisica pessoaFisica) {
        pessoasFisicas.add(pessoaFisica);
    }

    public void alterar(PessoaFisica pessoaAlterada) {
        for (int i = 0; i < pessoasFisicas.size(); i++) {
            PessoaFisica pessoaExistente = pessoasFisicas.get(i);
            if (pessoaExistente.getId() == pessoaAlterada.getId()) {
                pessoaExistente.setNome(pessoaAlterada.getNome());
                pessoaExistente.setCpf(pessoaAlterada.getCpf());
                pessoaExistente.setIdade(pessoaAlterada.getIdade());
            }
        }

    }

    public boolean excluir(int id) {
        return pessoasFisicas.removeIf(pessoa -> {
            return id == pessoa.getId();
        });
    }

    public Optional<PessoaFisica> obter(int id) {
        return pessoasFisicas.stream().filter(pessoa -> id == pessoa.getId()).findFirst();
    }

    public ArrayList<PessoaFisica> obterTodos() {
        return new ArrayList<>(pessoasFisicas);
    }

    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(pessoasFisicas);
        }
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            pessoasFisicas = (ArrayList<PessoaFisica>) ois.readObject();
        }
    }
}
