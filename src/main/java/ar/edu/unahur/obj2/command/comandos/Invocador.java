package ar.edu.unahur.obj2.command.comandos;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.command.Programable;

public class Invocador {
    private List<Operable> programas = new ArrayList<>();
    private Programable micro;

    public Invocador(List<Operable> programas, Programable micro) {
        this.programas = programas;
        this.micro = micro;
    }

    public void ejecuta(){
        this.programas.forEach(p -> p.execute(micro));    
    }

    public void deshacer(){
        if(programas.size() > 0){
            programas.get(programas.size() -1).undo(micro);
        }
        else{
            throw new IllegalArgumentException("No tiene programas para deshacer.");
        }
    }
}
