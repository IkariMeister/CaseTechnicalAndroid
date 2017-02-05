package com.jcgarcia.casetechtest.client.usecase;

import java.nio.ByteBuffer;

public class GeneradorTramasAleatorias {

  public static final byte INICIO_TRAMA[] = { (byte) 0xFF, (byte) 0xFF };
  public static final byte INICIO_DATOS[] = { (byte) 0xFF, (byte) 0x88 };
  public static final byte FIN_DATOS[] = { (byte) 0xEE, (byte) 0xEE };
  public static final byte FIN_TRAMA[] = { (byte) 0xEE, (byte) 0x88 };
  public static final short SEGUNDOS_POR_TRAMA = 1;
  private static final short DATA_SIZE = 2;
  private static final short DATOS_POR_SEGUNDO = 256;

  public static byte[] generaTrama() {
    int tramaSize = (6 + DATOS_POR_SEGUNDO * SEGUNDOS_POR_TRAMA) * DATA_SIZE;
    byte[] trama = new byte[tramaSize];
    byte[] firstTrama = { 0, 0 };
    byte[] dummyData = { 0, 1 };
    int indice = 0;

    indice = insertaSubTrama(trama, indice, INICIO_TRAMA);
    indice = insertaSubTrama(trama, indice, firstTrama);
    indice = insertaSubTrama(trama, indice, INICIO_DATOS);

    for (int i = 0; i < DATOS_POR_SEGUNDO * SEGUNDOS_POR_TRAMA; i++) {
      indice = insertaSubTrama(trama, indice, dummyData);
    }

    indice = insertaSubTrama(trama, indice, FIN_DATOS);
    indice = insertaSubTrama(trama, indice, intToByteArray(tramaSize));
    indice = insertaSubTrama(trama, indice, FIN_TRAMA);

    return trama;
  }

  public static int insertaSubTrama(byte[] arrayContenedor, int initialPosition, byte[] subTrama) {

    if (initialPosition < 0 || initialPosition >= arrayContenedor.length) {
      return -1;
    }

    int posicionFinal = initialPosition + subTrama.length;
    if (posicionFinal > arrayContenedor.length) {
      return -1;
    }

    for (int i = 0; i < subTrama.length; i++) {
      arrayContenedor[initialPosition + i] = subTrama[i];
    }

    return posicionFinal;
  }

  public static byte[] getByteArrayFromValue(int value, short arraySize) {
    byte[] byteArray4 = ByteBuffer.allocate(4).putInt(value).array();

    byte[] byteArrayCustom = new byte[arraySize];
    int diferencia = 4 - arraySize;
    for (int i = 0; i < arraySize; i++) {
      byteArrayCustom[i] = byteArray4[diferencia + i];
    }

    return byteArrayCustom;
  }

  public static byte[] intToByteArray(int a) {
    byte[] ret = new byte[2];
    ret[1] = (byte) (a & 0xFF);
    ret[0] = (byte) ((a >> 8) & 0xFF);
    return ret;
  }

  public static int getIntFromByteArray(byte[] array) {

    if (array == null) {
      return 0;
    }

    final int base = 16;
    int resultado = 0;

    for (int i = array.length - 1; i >= 0; i--) {
      int multiplicador = base ^ i;
      long valorPosicion = (array[i] & 0xFF) * multiplicador;
      resultado += valorPosicion;
    }

    return resultado;
  }
}
