/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.rapid.adaptor.ctp.serializable.avro.trader;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

/** 合约状态 */
@org.apache.avro.specific.AvroGenerated
public class FtdcInstrumentStatus extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 3166196660061317644L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"FtdcInstrumentStatus\",\"namespace\":\"io.rapid.adaptor.ctp.serializable.avro.trader\",\"doc\":\"合约状态\",\"fields\":[{\"name\":\"ExchangeID\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"交易所代码\"},{\"name\":\"ExchangeInstID\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"合约在交易所的代码\"},{\"name\":\"SettlementGroupID\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"结算组代码\"},{\"name\":\"InstrumentID\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"合约代码\"},{\"name\":\"InstrumentStatus\",\"type\":\"int\",\"doc\":\"合约交易状态\\n///开盘前\\n#define THOST_FTDC_IS_BeforeTrading '0'\\n///非交易\\n#define THOST_FTDC_IS_NoTrading '1'\\n///连续交易\\n#define THOST_FTDC_IS_Continous '2'\\n///集合竞价报单\\n#define THOST_FTDC_IS_AuctionOrdering '3'\\n///集合竞价价格平衡\\n#define THOST_FTDC_IS_AuctionBalance '4'\\n///集合竞价撮合\\n#define THOST_FTDC_IS_AuctionMatch '5'\\n///收盘\\n#define THOST_FTDC_IS_Closed '6'\"},{\"name\":\"TradingSegmentSN\",\"type\":\"int\",\"doc\":\"交易阶段编号\"},{\"name\":\"EnterTime\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"进入本状态时间\"},{\"name\":\"EnterReason\",\"type\":\"int\",\"doc\":\"进入本状态原因\\n///自动切换\\n#define THOST_FTDC_IER_Automatic '1'\\n///手动切换\\n#define THOST_FTDC_IER_Manual '2'\\n///熔断\\n#define THOST_FTDC_IER_Fuse '3'\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<FtdcInstrumentStatus> ENCODER =
      new BinaryMessageEncoder<FtdcInstrumentStatus>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<FtdcInstrumentStatus> DECODER =
      new BinaryMessageDecoder<FtdcInstrumentStatus>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<FtdcInstrumentStatus> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<FtdcInstrumentStatus> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<FtdcInstrumentStatus> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<FtdcInstrumentStatus>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this FtdcInstrumentStatus to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a FtdcInstrumentStatus from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a FtdcInstrumentStatus instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static FtdcInstrumentStatus fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** 交易所代码 */
  public java.lang.String ExchangeID;
  /** 合约在交易所的代码 */
  public java.lang.String ExchangeInstID;
  /** 结算组代码 */
  public java.lang.String SettlementGroupID;
  /** 合约代码 */
  public java.lang.String InstrumentID;
  /** 合约交易状态
///开盘前
#define THOST_FTDC_IS_BeforeTrading '0'
///非交易
#define THOST_FTDC_IS_NoTrading '1'
///连续交易
#define THOST_FTDC_IS_Continous '2'
///集合竞价报单
#define THOST_FTDC_IS_AuctionOrdering '3'
///集合竞价价格平衡
#define THOST_FTDC_IS_AuctionBalance '4'
///集合竞价撮合
#define THOST_FTDC_IS_AuctionMatch '5'
///收盘
#define THOST_FTDC_IS_Closed '6' */
  public int InstrumentStatus;
  /** 交易阶段编号 */
  public int TradingSegmentSN;
  /** 进入本状态时间 */
  public java.lang.String EnterTime;
  /** 进入本状态原因
///自动切换
#define THOST_FTDC_IER_Automatic '1'
///手动切换
#define THOST_FTDC_IER_Manual '2'
///熔断
#define THOST_FTDC_IER_Fuse '3' */
  public int EnterReason;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public FtdcInstrumentStatus() {}

  /**
   * All-args constructor.
   * @param ExchangeID 交易所代码
   * @param ExchangeInstID 合约在交易所的代码
   * @param SettlementGroupID 结算组代码
   * @param InstrumentID 合约代码
   * @param InstrumentStatus 合约交易状态
///开盘前
#define THOST_FTDC_IS_BeforeTrading '0'
///非交易
#define THOST_FTDC_IS_NoTrading '1'
///连续交易
#define THOST_FTDC_IS_Continous '2'
///集合竞价报单
#define THOST_FTDC_IS_AuctionOrdering '3'
///集合竞价价格平衡
#define THOST_FTDC_IS_AuctionBalance '4'
///集合竞价撮合
#define THOST_FTDC_IS_AuctionMatch '5'
///收盘
#define THOST_FTDC_IS_Closed '6'
   * @param TradingSegmentSN 交易阶段编号
   * @param EnterTime 进入本状态时间
   * @param EnterReason 进入本状态原因
///自动切换
#define THOST_FTDC_IER_Automatic '1'
///手动切换
#define THOST_FTDC_IER_Manual '2'
///熔断
#define THOST_FTDC_IER_Fuse '3'
   */
  public FtdcInstrumentStatus(java.lang.String ExchangeID, java.lang.String ExchangeInstID, java.lang.String SettlementGroupID, java.lang.String InstrumentID, java.lang.Integer InstrumentStatus, java.lang.Integer TradingSegmentSN, java.lang.String EnterTime, java.lang.Integer EnterReason) {
    this.ExchangeID = ExchangeID;
    this.ExchangeInstID = ExchangeInstID;
    this.SettlementGroupID = SettlementGroupID;
    this.InstrumentID = InstrumentID;
    this.InstrumentStatus = InstrumentStatus;
    this.TradingSegmentSN = TradingSegmentSN;
    this.EnterTime = EnterTime;
    this.EnterReason = EnterReason;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return ExchangeID;
    case 1: return ExchangeInstID;
    case 2: return SettlementGroupID;
    case 3: return InstrumentID;
    case 4: return InstrumentStatus;
    case 5: return TradingSegmentSN;
    case 6: return EnterTime;
    case 7: return EnterReason;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: ExchangeID = value$ != null ? value$.toString() : null; break;
    case 1: ExchangeInstID = value$ != null ? value$.toString() : null; break;
    case 2: SettlementGroupID = value$ != null ? value$.toString() : null; break;
    case 3: InstrumentID = value$ != null ? value$.toString() : null; break;
    case 4: InstrumentStatus = (java.lang.Integer)value$; break;
    case 5: TradingSegmentSN = (java.lang.Integer)value$; break;
    case 6: EnterTime = value$ != null ? value$.toString() : null; break;
    case 7: EnterReason = (java.lang.Integer)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'ExchangeID' field.
   * @return 交易所代码
   */
  public java.lang.String getExchangeID() {
    return ExchangeID;
  }


  /**
   * Sets the value of the 'ExchangeID' field.
   * 交易所代码
   * @param value the value to set.
   */
  public FtdcInstrumentStatus setExchangeID(java.lang.String value) {
    this.ExchangeID = value;
    return this;
  }

  /**
   * Gets the value of the 'ExchangeInstID' field.
   * @return 合约在交易所的代码
   */
  public java.lang.String getExchangeInstID() {
    return ExchangeInstID;
  }


  /**
   * Sets the value of the 'ExchangeInstID' field.
   * 合约在交易所的代码
   * @param value the value to set.
   */
  public FtdcInstrumentStatus setExchangeInstID(java.lang.String value) {
    this.ExchangeInstID = value;
    return this;
  }

  /**
   * Gets the value of the 'SettlementGroupID' field.
   * @return 结算组代码
   */
  public java.lang.String getSettlementGroupID() {
    return SettlementGroupID;
  }


  /**
   * Sets the value of the 'SettlementGroupID' field.
   * 结算组代码
   * @param value the value to set.
   */
  public FtdcInstrumentStatus setSettlementGroupID(java.lang.String value) {
    this.SettlementGroupID = value;
    return this;
  }

  /**
   * Gets the value of the 'InstrumentID' field.
   * @return 合约代码
   */
  public java.lang.String getInstrumentID() {
    return InstrumentID;
  }


  /**
   * Sets the value of the 'InstrumentID' field.
   * 合约代码
   * @param value the value to set.
   */
  public FtdcInstrumentStatus setInstrumentID(java.lang.String value) {
    this.InstrumentID = value;
    return this;
  }

  /**
   * Gets the value of the 'InstrumentStatus' field.
   * @return 合约交易状态
///开盘前
#define THOST_FTDC_IS_BeforeTrading '0'
///非交易
#define THOST_FTDC_IS_NoTrading '1'
///连续交易
#define THOST_FTDC_IS_Continous '2'
///集合竞价报单
#define THOST_FTDC_IS_AuctionOrdering '3'
///集合竞价价格平衡
#define THOST_FTDC_IS_AuctionBalance '4'
///集合竞价撮合
#define THOST_FTDC_IS_AuctionMatch '5'
///收盘
#define THOST_FTDC_IS_Closed '6'
   */
  public int getInstrumentStatus() {
    return InstrumentStatus;
  }


  /**
   * Sets the value of the 'InstrumentStatus' field.
   * 合约交易状态
///开盘前
#define THOST_FTDC_IS_BeforeTrading '0'
///非交易
#define THOST_FTDC_IS_NoTrading '1'
///连续交易
#define THOST_FTDC_IS_Continous '2'
///集合竞价报单
#define THOST_FTDC_IS_AuctionOrdering '3'
///集合竞价价格平衡
#define THOST_FTDC_IS_AuctionBalance '4'
///集合竞价撮合
#define THOST_FTDC_IS_AuctionMatch '5'
///收盘
#define THOST_FTDC_IS_Closed '6'
   * @param value the value to set.
   */
  public FtdcInstrumentStatus setInstrumentStatus(int value) {
    this.InstrumentStatus = value;
    return this;
  }

  /**
   * Gets the value of the 'TradingSegmentSN' field.
   * @return 交易阶段编号
   */
  public int getTradingSegmentSN() {
    return TradingSegmentSN;
  }


  /**
   * Sets the value of the 'TradingSegmentSN' field.
   * 交易阶段编号
   * @param value the value to set.
   */
  public FtdcInstrumentStatus setTradingSegmentSN(int value) {
    this.TradingSegmentSN = value;
    return this;
  }

  /**
   * Gets the value of the 'EnterTime' field.
   * @return 进入本状态时间
   */
  public java.lang.String getEnterTime() {
    return EnterTime;
  }


  /**
   * Sets the value of the 'EnterTime' field.
   * 进入本状态时间
   * @param value the value to set.
   */
  public FtdcInstrumentStatus setEnterTime(java.lang.String value) {
    this.EnterTime = value;
    return this;
  }

  /**
   * Gets the value of the 'EnterReason' field.
   * @return 进入本状态原因
///自动切换
#define THOST_FTDC_IER_Automatic '1'
///手动切换
#define THOST_FTDC_IER_Manual '2'
///熔断
#define THOST_FTDC_IER_Fuse '3'
   */
  public int getEnterReason() {
    return EnterReason;
  }


  /**
   * Sets the value of the 'EnterReason' field.
   * 进入本状态原因
///自动切换
#define THOST_FTDC_IER_Automatic '1'
///手动切换
#define THOST_FTDC_IER_Manual '2'
///熔断
#define THOST_FTDC_IER_Fuse '3'
   * @param value the value to set.
   */
  public FtdcInstrumentStatus setEnterReason(int value) {
    this.EnterReason = value;
    return this;
  }

  /**
   * Creates a new FtdcInstrumentStatus RecordBuilder.
   * @return A new FtdcInstrumentStatus RecordBuilder
   */
  public static io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder newBuilder() {
    return new io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder();
  }

  /**
   * Creates a new FtdcInstrumentStatus RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new FtdcInstrumentStatus RecordBuilder
   */
  public static io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder newBuilder(io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder other) {
    if (other == null) {
      return new io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder();
    } else {
      return new io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder(other);
    }
  }

  /**
   * Creates a new FtdcInstrumentStatus RecordBuilder by copying an existing FtdcInstrumentStatus instance.
   * @param other The existing instance to copy.
   * @return A new FtdcInstrumentStatus RecordBuilder
   */
  public static io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder newBuilder(io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus other) {
    if (other == null) {
      return new io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder();
    } else {
      return new io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder(other);
    }
  }

  /**
   * RecordBuilder for FtdcInstrumentStatus instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<FtdcInstrumentStatus>
    implements org.apache.avro.data.RecordBuilder<FtdcInstrumentStatus> {

    /** 交易所代码 */
    private java.lang.String ExchangeID;
    /** 合约在交易所的代码 */
    private java.lang.String ExchangeInstID;
    /** 结算组代码 */
    private java.lang.String SettlementGroupID;
    /** 合约代码 */
    private java.lang.String InstrumentID;
    /** 合约交易状态
///开盘前
#define THOST_FTDC_IS_BeforeTrading '0'
///非交易
#define THOST_FTDC_IS_NoTrading '1'
///连续交易
#define THOST_FTDC_IS_Continous '2'
///集合竞价报单
#define THOST_FTDC_IS_AuctionOrdering '3'
///集合竞价价格平衡
#define THOST_FTDC_IS_AuctionBalance '4'
///集合竞价撮合
#define THOST_FTDC_IS_AuctionMatch '5'
///收盘
#define THOST_FTDC_IS_Closed '6' */
    private int InstrumentStatus;
    /** 交易阶段编号 */
    private int TradingSegmentSN;
    /** 进入本状态时间 */
    private java.lang.String EnterTime;
    /** 进入本状态原因
///自动切换
#define THOST_FTDC_IER_Automatic '1'
///手动切换
#define THOST_FTDC_IER_Manual '2'
///熔断
#define THOST_FTDC_IER_Fuse '3' */
    private int EnterReason;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.ExchangeID)) {
        this.ExchangeID = data().deepCopy(fields()[0].schema(), other.ExchangeID);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.ExchangeInstID)) {
        this.ExchangeInstID = data().deepCopy(fields()[1].schema(), other.ExchangeInstID);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.SettlementGroupID)) {
        this.SettlementGroupID = data().deepCopy(fields()[2].schema(), other.SettlementGroupID);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.InstrumentID)) {
        this.InstrumentID = data().deepCopy(fields()[3].schema(), other.InstrumentID);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.InstrumentStatus)) {
        this.InstrumentStatus = data().deepCopy(fields()[4].schema(), other.InstrumentStatus);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.TradingSegmentSN)) {
        this.TradingSegmentSN = data().deepCopy(fields()[5].schema(), other.TradingSegmentSN);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.EnterTime)) {
        this.EnterTime = data().deepCopy(fields()[6].schema(), other.EnterTime);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.EnterReason)) {
        this.EnterReason = data().deepCopy(fields()[7].schema(), other.EnterReason);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
    }

    /**
     * Creates a Builder by copying an existing FtdcInstrumentStatus instance
     * @param other The existing instance to copy.
     */
    private Builder(io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.ExchangeID)) {
        this.ExchangeID = data().deepCopy(fields()[0].schema(), other.ExchangeID);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.ExchangeInstID)) {
        this.ExchangeInstID = data().deepCopy(fields()[1].schema(), other.ExchangeInstID);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.SettlementGroupID)) {
        this.SettlementGroupID = data().deepCopy(fields()[2].schema(), other.SettlementGroupID);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.InstrumentID)) {
        this.InstrumentID = data().deepCopy(fields()[3].schema(), other.InstrumentID);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.InstrumentStatus)) {
        this.InstrumentStatus = data().deepCopy(fields()[4].schema(), other.InstrumentStatus);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.TradingSegmentSN)) {
        this.TradingSegmentSN = data().deepCopy(fields()[5].schema(), other.TradingSegmentSN);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.EnterTime)) {
        this.EnterTime = data().deepCopy(fields()[6].schema(), other.EnterTime);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.EnterReason)) {
        this.EnterReason = data().deepCopy(fields()[7].schema(), other.EnterReason);
        fieldSetFlags()[7] = true;
      }
    }

    /**
      * Gets the value of the 'ExchangeID' field.
      * 交易所代码
      * @return The value.
      */
    public java.lang.String getExchangeID() {
      return ExchangeID;
    }


    /**
      * Sets the value of the 'ExchangeID' field.
      * 交易所代码
      * @param value The value of 'ExchangeID'.
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder setExchangeID(java.lang.String value) {
      validate(fields()[0], value);
      this.ExchangeID = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'ExchangeID' field has been set.
      * 交易所代码
      * @return True if the 'ExchangeID' field has been set, false otherwise.
      */
    public boolean hasExchangeID() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'ExchangeID' field.
      * 交易所代码
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder clearExchangeID() {
      ExchangeID = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'ExchangeInstID' field.
      * 合约在交易所的代码
      * @return The value.
      */
    public java.lang.String getExchangeInstID() {
      return ExchangeInstID;
    }


    /**
      * Sets the value of the 'ExchangeInstID' field.
      * 合约在交易所的代码
      * @param value The value of 'ExchangeInstID'.
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder setExchangeInstID(java.lang.String value) {
      validate(fields()[1], value);
      this.ExchangeInstID = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'ExchangeInstID' field has been set.
      * 合约在交易所的代码
      * @return True if the 'ExchangeInstID' field has been set, false otherwise.
      */
    public boolean hasExchangeInstID() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'ExchangeInstID' field.
      * 合约在交易所的代码
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder clearExchangeInstID() {
      ExchangeInstID = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'SettlementGroupID' field.
      * 结算组代码
      * @return The value.
      */
    public java.lang.String getSettlementGroupID() {
      return SettlementGroupID;
    }


    /**
      * Sets the value of the 'SettlementGroupID' field.
      * 结算组代码
      * @param value The value of 'SettlementGroupID'.
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder setSettlementGroupID(java.lang.String value) {
      validate(fields()[2], value);
      this.SettlementGroupID = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'SettlementGroupID' field has been set.
      * 结算组代码
      * @return True if the 'SettlementGroupID' field has been set, false otherwise.
      */
    public boolean hasSettlementGroupID() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'SettlementGroupID' field.
      * 结算组代码
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder clearSettlementGroupID() {
      SettlementGroupID = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'InstrumentID' field.
      * 合约代码
      * @return The value.
      */
    public java.lang.String getInstrumentID() {
      return InstrumentID;
    }


    /**
      * Sets the value of the 'InstrumentID' field.
      * 合约代码
      * @param value The value of 'InstrumentID'.
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder setInstrumentID(java.lang.String value) {
      validate(fields()[3], value);
      this.InstrumentID = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'InstrumentID' field has been set.
      * 合约代码
      * @return True if the 'InstrumentID' field has been set, false otherwise.
      */
    public boolean hasInstrumentID() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'InstrumentID' field.
      * 合约代码
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder clearInstrumentID() {
      InstrumentID = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'InstrumentStatus' field.
      * 合约交易状态
///开盘前
#define THOST_FTDC_IS_BeforeTrading '0'
///非交易
#define THOST_FTDC_IS_NoTrading '1'
///连续交易
#define THOST_FTDC_IS_Continous '2'
///集合竞价报单
#define THOST_FTDC_IS_AuctionOrdering '3'
///集合竞价价格平衡
#define THOST_FTDC_IS_AuctionBalance '4'
///集合竞价撮合
#define THOST_FTDC_IS_AuctionMatch '5'
///收盘
#define THOST_FTDC_IS_Closed '6'
      * @return The value.
      */
    public int getInstrumentStatus() {
      return InstrumentStatus;
    }


    /**
      * Sets the value of the 'InstrumentStatus' field.
      * 合约交易状态
///开盘前
#define THOST_FTDC_IS_BeforeTrading '0'
///非交易
#define THOST_FTDC_IS_NoTrading '1'
///连续交易
#define THOST_FTDC_IS_Continous '2'
///集合竞价报单
#define THOST_FTDC_IS_AuctionOrdering '3'
///集合竞价价格平衡
#define THOST_FTDC_IS_AuctionBalance '4'
///集合竞价撮合
#define THOST_FTDC_IS_AuctionMatch '5'
///收盘
#define THOST_FTDC_IS_Closed '6'
      * @param value The value of 'InstrumentStatus'.
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder setInstrumentStatus(int value) {
      validate(fields()[4], value);
      this.InstrumentStatus = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'InstrumentStatus' field has been set.
      * 合约交易状态
///开盘前
#define THOST_FTDC_IS_BeforeTrading '0'
///非交易
#define THOST_FTDC_IS_NoTrading '1'
///连续交易
#define THOST_FTDC_IS_Continous '2'
///集合竞价报单
#define THOST_FTDC_IS_AuctionOrdering '3'
///集合竞价价格平衡
#define THOST_FTDC_IS_AuctionBalance '4'
///集合竞价撮合
#define THOST_FTDC_IS_AuctionMatch '5'
///收盘
#define THOST_FTDC_IS_Closed '6'
      * @return True if the 'InstrumentStatus' field has been set, false otherwise.
      */
    public boolean hasInstrumentStatus() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'InstrumentStatus' field.
      * 合约交易状态
///开盘前
#define THOST_FTDC_IS_BeforeTrading '0'
///非交易
#define THOST_FTDC_IS_NoTrading '1'
///连续交易
#define THOST_FTDC_IS_Continous '2'
///集合竞价报单
#define THOST_FTDC_IS_AuctionOrdering '3'
///集合竞价价格平衡
#define THOST_FTDC_IS_AuctionBalance '4'
///集合竞价撮合
#define THOST_FTDC_IS_AuctionMatch '5'
///收盘
#define THOST_FTDC_IS_Closed '6'
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder clearInstrumentStatus() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'TradingSegmentSN' field.
      * 交易阶段编号
      * @return The value.
      */
    public int getTradingSegmentSN() {
      return TradingSegmentSN;
    }


    /**
      * Sets the value of the 'TradingSegmentSN' field.
      * 交易阶段编号
      * @param value The value of 'TradingSegmentSN'.
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder setTradingSegmentSN(int value) {
      validate(fields()[5], value);
      this.TradingSegmentSN = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'TradingSegmentSN' field has been set.
      * 交易阶段编号
      * @return True if the 'TradingSegmentSN' field has been set, false otherwise.
      */
    public boolean hasTradingSegmentSN() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'TradingSegmentSN' field.
      * 交易阶段编号
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder clearTradingSegmentSN() {
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'EnterTime' field.
      * 进入本状态时间
      * @return The value.
      */
    public java.lang.String getEnterTime() {
      return EnterTime;
    }


    /**
      * Sets the value of the 'EnterTime' field.
      * 进入本状态时间
      * @param value The value of 'EnterTime'.
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder setEnterTime(java.lang.String value) {
      validate(fields()[6], value);
      this.EnterTime = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'EnterTime' field has been set.
      * 进入本状态时间
      * @return True if the 'EnterTime' field has been set, false otherwise.
      */
    public boolean hasEnterTime() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'EnterTime' field.
      * 进入本状态时间
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder clearEnterTime() {
      EnterTime = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'EnterReason' field.
      * 进入本状态原因
///自动切换
#define THOST_FTDC_IER_Automatic '1'
///手动切换
#define THOST_FTDC_IER_Manual '2'
///熔断
#define THOST_FTDC_IER_Fuse '3'
      * @return The value.
      */
    public int getEnterReason() {
      return EnterReason;
    }


    /**
      * Sets the value of the 'EnterReason' field.
      * 进入本状态原因
///自动切换
#define THOST_FTDC_IER_Automatic '1'
///手动切换
#define THOST_FTDC_IER_Manual '2'
///熔断
#define THOST_FTDC_IER_Fuse '3'
      * @param value The value of 'EnterReason'.
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder setEnterReason(int value) {
      validate(fields()[7], value);
      this.EnterReason = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'EnterReason' field has been set.
      * 进入本状态原因
///自动切换
#define THOST_FTDC_IER_Automatic '1'
///手动切换
#define THOST_FTDC_IER_Manual '2'
///熔断
#define THOST_FTDC_IER_Fuse '3'
      * @return True if the 'EnterReason' field has been set, false otherwise.
      */
    public boolean hasEnterReason() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'EnterReason' field.
      * 进入本状态原因
///自动切换
#define THOST_FTDC_IER_Automatic '1'
///手动切换
#define THOST_FTDC_IER_Manual '2'
///熔断
#define THOST_FTDC_IER_Fuse '3'
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.trader.FtdcInstrumentStatus.Builder clearEnterReason() {
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    public FtdcInstrumentStatus build() {
      try {
        FtdcInstrumentStatus record = new FtdcInstrumentStatus();
        record.ExchangeID = fieldSetFlags()[0] ? this.ExchangeID : (java.lang.String) defaultValue(fields()[0]);
        record.ExchangeInstID = fieldSetFlags()[1] ? this.ExchangeInstID : (java.lang.String) defaultValue(fields()[1]);
        record.SettlementGroupID = fieldSetFlags()[2] ? this.SettlementGroupID : (java.lang.String) defaultValue(fields()[2]);
        record.InstrumentID = fieldSetFlags()[3] ? this.InstrumentID : (java.lang.String) defaultValue(fields()[3]);
        record.InstrumentStatus = fieldSetFlags()[4] ? this.InstrumentStatus : (java.lang.Integer) defaultValue(fields()[4]);
        record.TradingSegmentSN = fieldSetFlags()[5] ? this.TradingSegmentSN : (java.lang.Integer) defaultValue(fields()[5]);
        record.EnterTime = fieldSetFlags()[6] ? this.EnterTime : (java.lang.String) defaultValue(fields()[6]);
        record.EnterReason = fieldSetFlags()[7] ? this.EnterReason : (java.lang.Integer) defaultValue(fields()[7]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<FtdcInstrumentStatus>
    WRITER$ = (org.apache.avro.io.DatumWriter<FtdcInstrumentStatus>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<FtdcInstrumentStatus>
    READER$ = (org.apache.avro.io.DatumReader<FtdcInstrumentStatus>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.ExchangeID);

    out.writeString(this.ExchangeInstID);

    out.writeString(this.SettlementGroupID);

    out.writeString(this.InstrumentID);

    out.writeInt(this.InstrumentStatus);

    out.writeInt(this.TradingSegmentSN);

    out.writeString(this.EnterTime);

    out.writeInt(this.EnterReason);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.ExchangeID = in.readString();

      this.ExchangeInstID = in.readString();

      this.SettlementGroupID = in.readString();

      this.InstrumentID = in.readString();

      this.InstrumentStatus = in.readInt();

      this.TradingSegmentSN = in.readInt();

      this.EnterTime = in.readString();

      this.EnterReason = in.readInt();

    } else {
      for (int i = 0; i < 8; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.ExchangeID = in.readString();
          break;

        case 1:
          this.ExchangeInstID = in.readString();
          break;

        case 2:
          this.SettlementGroupID = in.readString();
          break;

        case 3:
          this.InstrumentID = in.readString();
          break;

        case 4:
          this.InstrumentStatus = in.readInt();
          break;

        case 5:
          this.TradingSegmentSN = in.readInt();
          break;

        case 6:
          this.EnterTime = in.readString();
          break;

        case 7:
          this.EnterReason = in.readInt();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










