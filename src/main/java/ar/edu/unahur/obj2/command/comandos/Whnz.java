package ar.edu.unahur.obj2.command.comandos;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.command.Programable;

public class Whnz implements Operable{

    private List<Operable> operaciones = new ArrayList<>();
    private Programable microCopia;

    public Whnz(List<Operable> operaciones) {
        this.operaciones = operaciones;
    }

    @Override
    public void execute(Programable micro) {
        microCopia = micro.copy();
        micro.incProgramCounter();
        while(micro.getAcumuladorA() != 0){
            operaciones.forEach(o -> o.execute(micro));
        }
    }

    @Override
    public void undo(Programable micro) {
        micro.copyFrom(microCopia);
    }

}
