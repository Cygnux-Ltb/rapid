/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.rapid.adaptor.ctp.serializable.avro.pack;
/** FTDC响应类型 */
@org.apache.avro.specific.AvroGenerated
public enum FtdcRspType implements org.apache.avro.generic.GenericEnumSymbol<FtdcRspType> {
  Unsupported, FrontDisconnected, HeartBeatWarning, RspUserLogin, UserLogout, RspError, MdClosed, TraderClosed, FtdcDepthMarketData, FtdcSpecificInstrument, FtdcInputOrder, FtdcInputOrderAction, FtdcInstrumentStatus, FtdcInvestorPosition, FtdcOrder, FtdcOrderAction, FtdcTrade, FtdcTradingAccount  ;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"FtdcRspType\",\"namespace\":\"io.rapid.adaptor.ctp.serializable.avro.pack\",\"doc\":\"FTDC响应类型\",\"symbols\":[\"Unsupported\",\"FrontDisconnected\",\"HeartBeatWarning\",\"RspUserLogin\",\"UserLogout\",\"RspError\",\"MdClosed\",\"TraderClosed\",\"FtdcDepthMarketData\",\"FtdcSpecificInstrument\",\"FtdcInputOrder\",\"FtdcInputOrderAction\",\"FtdcInstrumentStatus\",\"FtdcInvestorPosition\",\"FtdcOrder\",\"FtdcOrderAction\",\"FtdcTrade\",\"FtdcTradingAccount\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
}