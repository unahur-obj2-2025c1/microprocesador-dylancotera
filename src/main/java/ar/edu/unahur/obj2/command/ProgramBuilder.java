package ar.edu.unahur.obj2.command;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.command.comandos.Add;
import ar.edu.unahur.obj2.command.comandos.Ifnz;
import ar.edu.unahur.obj2.command.comandos.Lod;
import ar.edu.unahur.obj2.command.comandos.Lodv;
import ar.edu.unahur.obj2.command.comandos.Nop;
import ar.edu.unahur.obj2.command.comandos.Operable;
import ar.edu.unahur.obj2.command.comandos.Str;
import ar.edu.unahur.obj2.command.comandos.Swap;

public class ProgramBuilder {

    private List<Operable> operaciones = new ArrayList<>();

    public ProgramBuilder() {
    }

    public ProgramBuilder nop(){
        operaciones.add(new Nop());
        return this;
    }

    public ProgramBuilder add(){
        operaciones.add(new Add());
        return this;
    }

    public ProgramBuilder swap(){
        operaciones.add(new Swap());
        return this;
    }

    public ProgramBuilder lod(Integer addr){
        operaciones.add(new Lod(addr));
        return this;
    }

    public ProgramBuilder str(Integer addr){
        operaciones.add(new Str(addr));
        return this;
    }

    public ProgramBuilder lodv(Integer val){
        operaciones.add(new Lodv(val));
        return this;
    }

    public ProgramBuilder ifnz(List<Operable> op){
        operaciones.add(new Ifnz(op));
        return this;
    }

    public List<Operable> build(){
        return operaciones;
    }

}
