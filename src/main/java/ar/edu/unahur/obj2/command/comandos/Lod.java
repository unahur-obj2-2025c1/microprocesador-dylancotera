package ar.edu.unahur.obj2.command.comandos;

import ar.edu.unahur.obj2.command.Programable;

public class Lod implements Operable{

    private Integer addr;
    private Programable microCopia;
    public Lod(Integer addr) {
        this.addr = addr;
    }

    @Override
    public void execute(Programable micro) {
        microCopia = micro.copy();

        micro.incProgramCounter();
        if(addr >= 0 && addr < 1024){
            micro.setAcumuladorA(micro.getAddr(addr));
        }
        else{
            throw new IllegalArgumentException("Dirección de memoria fuera de rango");
        }
    }

    @Override
    public void undo(Programable micro) {
        micro.copyFrom(microCopia);
    }
    
}
