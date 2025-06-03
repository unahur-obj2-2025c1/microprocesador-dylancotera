package ar.edu.unahur.obj2.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.command.comandos.Add;
import ar.edu.unahur.obj2.command.comandos.Ifnz;
import ar.edu.unahur.obj2.command.comandos.Invocador;
import ar.edu.unahur.obj2.command.comandos.Lod;
import ar.edu.unahur.obj2.command.comandos.Lodv;
import ar.edu.unahur.obj2.command.comandos.Nop;
import ar.edu.unahur.obj2.command.comandos.Operable;
import ar.edu.unahur.obj2.command.comandos.Str;
import ar.edu.unahur.obj2.command.comandos.Swap;
import ar.edu.unahur.obj2.command.comandos.Whnz;

public class MicroprocesadorTest {
    Programable micro = new Microprocesador(0, 0, 0);
    Invocador invocador = new Invocador(Arrays.asList(new Nop(), new Nop(), new Nop()),micro);
    Invocador invocador2 = new Invocador(Arrays.asList(
        new Lodv(20), new Swap(), new Lodv(17), new Add()),micro);
    Invocador invocador3 = new Invocador(Arrays.asList(
        new Lodv(2), new Str(0), new Lodv(8), new Swap(), new Lodv(5), new Add(), new Swap(), new Lod(0), new Add()), micro);

    List<Operable> operaciones = new ProgramBuilder().nop().nop().nop().ifnz(new ProgramBuilder().nop().build()).build();
                                                                    //.ifnz(new Nop()).build();                                      
    Ifnz ifnz = new Ifnz(Arrays.asList(new Nop(), new Nop()));
    Invocador invocador4 = new Invocador(Arrays.asList(new Swap(),
        ifnz), micro);
    

    @Test
    void HacerAvanzar3PosicionesElPc() {
        invocador.ejecuta();
        assertEquals(3, micro.getProgramCounter());    
    }
    @Test
    void Sumar20_Mas_17() {
        invocador2.ejecuta();
        assertEquals(4, micro.getProgramCounter());  
        assertEquals(37, micro.getAcumuladorA());
        assertEquals(0, micro.getAcumuladorB());
    }
    @Test
    void Sumar2_mas_8_mas_5() {
        invocador3.ejecuta();
        assertEquals(9, micro.getProgramCounter());  
        assertEquals(15, micro.getAcumuladorA());
        assertEquals(0, micro.getAcumuladorB());
    }

    @Test
    void LeEnvioLaOperacionAlRunDelMicroConTresNop(){
        micro.run(operaciones);
        assertEquals(3, micro.getProgramCounter());    
    }
    @Test
    void Sumar20_Mas_17YDeshagoLaUltimaOperacionADD() {
        invocador2.ejecuta();
        assertEquals(4, micro.getProgramCounter());  
        assertEquals(37, micro.getAcumuladorA());
        assertEquals(0, micro.getAcumuladorB());
        invocador2.deshacer();
        assertEquals(17, micro.getAcumuladorA());
        assertEquals(20, micro.getAcumuladorB());
    }
    @Test
    void PruebaDeshacerConAdd(){
        Programable micro2 = new Microprocesador(10, 5, 0);
        Invocador inv = new Invocador(Arrays.asList(new Add()), micro2);
        inv.ejecuta();
        assertEquals(micro2.getAcumuladorA(), 15);
        assertEquals(micro2.getAcumuladorB(), 0);
        inv.deshacer();
        assertEquals(10, micro2.getAcumuladorA());
        assertEquals(5, micro2.getAcumuladorB());
    }
    @Test
    void PruebaDeshacerConSwap(){
        Programable micro2 = new Microprocesador(10, 5, 0);
        Invocador inv = new Invocador(Arrays.asList(new Swap()), micro2);
        inv.ejecuta();
        assertEquals(micro2.getAcumuladorA(), 5);
        assertEquals(micro2.getAcumuladorB(), 10);
        inv.deshacer();
        assertEquals(10, micro2.getAcumuladorA());
        assertEquals(5, micro2.getAcumuladorB());
    }
    @Test
    void PruebaDeshacerConLod(){
        Programable micro2 = new Microprocesador(10, 5, 0);
        Invocador inv = new Invocador(Arrays.asList(new Lod(0), new Lod(1)), micro2);
        inv.ejecuta();
        assertEquals(micro2.getAcumuladorA(), 0);
        assertEquals(micro2.getAcumuladorB(), 5);
        assertEquals(0, micro2.getAddr(0));
        inv.deshacer();
        assertEquals(0, micro2.getAcumuladorA());
        assertEquals(5, micro2.getAcumuladorB());
        assertEquals(10, micro2.getAddr(0));
    }
    @Test
    void PruebaDeshacerConStr(){
        Programable micro2 = new Microprocesador(10, 5, 0);
        Invocador inv = new Invocador(Arrays.asList(new Str(0), new Lodv(2), new Str(1)), micro2);
        inv.ejecuta();
        assertEquals(micro2.getAcumuladorA(), 0);
        assertEquals(micro2.getAcumuladorB(), 5);
        assertEquals(10, micro2.getAddr(0));
        assertEquals(3, micro2.getProgramCounter());
        inv.deshacer();
        assertEquals(2, micro2.getAcumuladorA());
        assertEquals(5, micro2.getAcumuladorB());
        assertEquals(0, micro2.getAddr(1));
        assertEquals(2, micro2.getProgramCounter());
    }
    @Test
    void PruebaDeshacerConLODV(){
        Programable micro2 = new Microprocesador(0, 5, 0);
        Invocador inv = new Invocador(Arrays.asList(new Lodv(20)), micro2);
        inv.ejecuta();
        assertEquals(micro2.getAcumuladorA(), 20);
        assertEquals(micro2.getAcumuladorB(), 5);
        assertEquals(1, micro2.getProgramCounter());
        inv.deshacer();
        assertEquals(0, micro2.getAcumuladorA());
        assertEquals(5, micro2.getAcumuladorB());
        assertEquals(0, micro2.getProgramCounter());
    }
    @Test
    void Prueba_IFNZ_ConInvocador(){
        Programable micro = new Microprocesador(10, 5, 0);
        
        Ifnz ifnz = new Ifnz(Arrays.asList(new Nop(), new Nop()));
        Invocador invocador4 = new Invocador(Arrays.asList(new Swap(),
        ifnz), micro);
        invocador4.ejecuta();

        assertEquals(micro.getAcumuladorA(), 5);
        assertEquals(micro.getAcumuladorB(), 10);
        assertEquals(4, micro.getProgramCounter());
        invocador4.deshacer();
        assertEquals(1, micro.getProgramCounter());

    }
    @Test
    void Prueba_WHNZ_ConInvocador(){
        Programable micro = new Microprocesador(10, 0, 0);
        
        Whnz whnz = new Whnz(Arrays.asList(new Nop(), new Nop()));
        Invocador invocador4 = new Invocador(Arrays.asList(new Swap(),
        whnz), micro);
        invocador4.ejecuta();

        assertEquals(micro.getAcumuladorA(), 0);
        assertEquals(micro.getAcumuladorB(), 10);
        assertEquals(2, micro.getProgramCounter());

    }
}
