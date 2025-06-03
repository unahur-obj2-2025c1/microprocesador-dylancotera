package ar.edu.unahur.obj2.command.comandos;

import ar.edu.unahur.obj2.command.Programable;

public class Nop implements Operable{

    @Override
    public void execute(Programable micro) {
        micro.incProgramCounter();
    }

    @Override
    public void undo(Programable micro) {
        
    }

}
