/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.rapid.core.protocol.avro.enums;
@org.apache.avro.specific.AvroGenerated
public enum OrdType implements org.apache.avro.generic.GenericEnumSymbol<OrdType> {
  INVALID, LIMITED, MARKET, LIMITED_STOP, MARKET_STOP, MTL, BP, AP, FOK, FAK, MV  ;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"OrdType\",\"namespace\":\"io.rapid.core.protocol.avro.enums\",\"symbols\":[\"INVALID\",\"LIMITED\",\"MARKET\",\"LIMITED_STOP\",\"MARKET_STOP\",\"MTL\",\"BP\",\"AP\",\"FOK\",\"FAK\",\"MV\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
}
