package ar.edu.unahur.obj2.command.comandos;

import ar.edu.unahur.obj2.command.Programable;

public class Add implements Operable{

    private Programable microCopia;

    @Override
    public void execute(Programable micro) {
        microCopia = micro.copy();

        Integer suma = micro.getAcumuladorA() + micro.getAcumuladorB();
        micro.setAcumuladorA(suma);
        micro.setAcumuladorB(0);
        micro.incProgramCounter();
    }

    @Override
    public void undo(Programable micro) {
        micro.copyFrom(microCopia);
        //micro.setAcumuladorA(microCopia.getAcumuladorA());
        //micro.setAcumuladorB(microCopia.getAcumuladorB());
    }

}
