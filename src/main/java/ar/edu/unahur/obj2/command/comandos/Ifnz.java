package ar.edu.unahur.obj2.command.comandos;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.command.Programable;

public class Ifnz implements Operable{

    private List<Operable> operaciones = new ArrayList<>();
    private Programable microCopia;

    public Ifnz(List<Operable> operaciones) {
        this.operaciones = operaciones;
    }

    public Ifnz() {
    }

    @Override
    public void execute(Programable micro) {
        microCopia = micro.copy();
        micro.incProgramCounter();
        if(micro.getAcumuladorA() != 0){
            operaciones.forEach(o -> o.execute(micro));
        }
    }

    @Override
    public void undo(Programable micro) {
        micro.copyFrom(microCopia);
    }

}
