/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.rapid.adaptor.ctp.serializable.avro.md;
/** SpecificInstrument 来源 */
@org.apache.avro.specific.AvroGenerated
public enum SpecificInstrumentSource implements org.apache.avro.generic.GenericEnumSymbol<SpecificInstrumentSource> {
  SubMarketData, UnsubMarketData, SubForQuoteRsp, UnsubForQuoteRsp  ;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"SpecificInstrumentSource\",\"namespace\":\"io.rapid.adaptor.ctp.serializable.avro.md\",\"doc\":\"SpecificInstrument 来源\",\"symbols\":[\"SubMarketData\",\"UnsubMarketData\",\"SubForQuoteRsp\",\"UnsubForQuoteRsp\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
}
