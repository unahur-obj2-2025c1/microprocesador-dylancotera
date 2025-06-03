package ar.edu.unahur.obj2.command.comandos;

import ar.edu.unahur.obj2.command.Programable;

public class Swap implements Operable{

    private Programable microCopia;

    @Override
    public void execute(Programable micro) {
        microCopia = micro.copy();

        Integer valorDeA = micro.getAcumuladorA();

        micro.setAcumuladorA(micro.getAcumuladorB());
        micro.setAcumuladorB(valorDeA);
        micro.incProgramCounter();
    }

    @Override
    public void undo(Programable micro) {
        micro.copyFrom(microCopia);
        //micro.setAcumuladorA(micro.copy().getAcumuladorA());
        //micro.setAcumuladorB(micro.copy().getAcumuladorB());
    }

}
