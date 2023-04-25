package br.com.alura.screenmatch.excecao;

public class ErroAnoException extends RuntimeException {

    private String msg;
    public ErroAnoException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
