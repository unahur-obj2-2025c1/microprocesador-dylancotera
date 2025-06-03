package ar.edu.unahur.obj2.command.comandos;

import ar.edu.unahur.obj2.command.Programable;

public class Lodv implements Operable{

    private Integer val;
    private Programable microCopia;

    public Lodv(Integer val) {
        this.val = val;
    }

    @Override
    public void execute(Programable micro) {
        microCopia = micro.copy();
        
        micro.setAcumuladorA(val);
        micro.incProgramCounter();
    }

    @Override
    public void undo(Programable micro) {
        micro.copyFrom(microCopia);
    }
    
}
