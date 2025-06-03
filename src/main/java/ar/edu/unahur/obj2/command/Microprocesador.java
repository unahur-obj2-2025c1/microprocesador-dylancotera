package ar.edu.unahur.obj2.command;

import java.util.List;

import ar.edu.unahur.obj2.command.comandos.Operable;

public class Microprocesador implements Programable{

    private Integer acumuladorA;
    private Integer acumuladorB;
    private Integer programCounter;
    private int[] memoria = new int[1024];

    public Microprocesador(Integer acumuladorA, Integer acumuladorB, Integer programCounter) {
        this.acumuladorA = acumuladorA;
        this.acumuladorB = acumuladorB;
        this.programCounter = programCounter;
    }

    public Microprocesador(Integer acumuladorA, Integer acumuladorB, Integer programCounter, int[] memoria) {
        this.acumuladorA = acumuladorA;
        this.acumuladorB = acumuladorB;
        this.programCounter = programCounter;
        this.memoria = memoria;
    }

    @Override
    public void run(List<Operable> operaciones) {
        operaciones.forEach(o -> o.execute(this));
    }

    @Override
    public void incProgramCounter() {
        this.programCounter++;
    }

    @Override
    public Integer getProgramCounter() {
        return this.programCounter;
    }

    @Override
    public void setAcumuladorA(Integer value) {
        this.acumuladorA = value;
    }

    @Override
    public Integer getAcumuladorA() {
        return acumuladorA;
    }

    @Override
    public void setAcumuladorB(Integer value) {
        acumuladorB = value;
    }

    @Override
    public Integer getAcumuladorB() {
        return acumuladorB;
    }

    @Override
    public void copyFrom(Programable programable) {
        this.acumuladorA = programable.getAcumuladorA();
        this.acumuladorB = programable.getAcumuladorB();
        this.programCounter = programable.getProgramCounter();
        for (int i = 0; i < 1024; i++) {
        this.memoria[i] = programable.getAddr(i);
        }
    }

    @Override
    public Programable copy() {
        return new Microprocesador(acumuladorA, acumuladorB, programCounter);
    }

    @Override
    public void reset() {
        acumuladorA = 0;
        acumuladorB = 0;
        memoria = new int[1024];
    }

    @Override
    public void setAddr(Integer addr) {
        memoria[addr] = acumuladorA; 
    }

    @Override
    public Integer getAddr(Integer addr) {
        return memoria[addr];
    }

}