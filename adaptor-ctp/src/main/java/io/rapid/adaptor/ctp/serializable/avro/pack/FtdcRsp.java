/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.rapid.adaptor.ctp.serializable.avro.pack;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

/** FTDC响应包装 */
@org.apache.avro.specific.AvroGenerated
public class FtdcRsp extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -4128823029479761782L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"FtdcRsp\",\"namespace\":\"io.rapid.adaptor.ctp.serializable.avro.pack\",\"doc\":\"FTDC响应包装\",\"fields\":[{\"name\":\"RspType\",\"type\":{\"type\":\"enum\",\"name\":\"FtdcRspType\",\"doc\":\"FTDC响应类型\",\"symbols\":[\"Unsupported\",\"FrontDisconnected\",\"HeartBeatWarning\",\"RspUserLogin\",\"UserLogout\",\"RspError\",\"MdClosed\",\"TraderClosed\",\"FtdcDepthMarketData\",\"FtdcSpecificInstrument\",\"FtdcInputOrder\",\"FtdcInputOrderAction\",\"FtdcInstrumentStatus\",\"FtdcInvestorPosition\",\"FtdcOrder\",\"FtdcOrderAction\",\"FtdcTrade\",\"FtdcTradingAccount\"]},\"doc\":\"响应类型\"},{\"name\":\"epochMillis\",\"type\":\"long\",\"doc\":\"时间戳\"},{\"name\":\"RspContent\",\"type\":\"bytes\",\"doc\":\"FTDC响应内容 - 已序列化\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<FtdcRsp> ENCODER =
      new BinaryMessageEncoder<FtdcRsp>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<FtdcRsp> DECODER =
      new BinaryMessageDecoder<FtdcRsp>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<FtdcRsp> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<FtdcRsp> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<FtdcRsp> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<FtdcRsp>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this FtdcRsp to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a FtdcRsp from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a FtdcRsp instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static FtdcRsp fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** 响应类型 */
  public io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRspType RspType;
  /** 时间戳 */
  public long epochMillis;
  /** FTDC响应内容 - 已序列化 */
  public java.nio.ByteBuffer RspContent;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public FtdcRsp() {}

  /**
   * All-args constructor.
   * @param RspType 响应类型
   * @param epochMillis 时间戳
   * @param RspContent FTDC响应内容 - 已序列化
   */
  public FtdcRsp(io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRspType RspType, java.lang.Long epochMillis, java.nio.ByteBuffer RspContent) {
    this.RspType = RspType;
    this.epochMillis = epochMillis;
    this.RspContent = RspContent;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return RspType;
    case 1: return epochMillis;
    case 2: return RspContent;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: RspType = (io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRspType)value$; break;
    case 1: epochMillis = (java.lang.Long)value$; break;
    case 2: RspContent = (java.nio.ByteBuffer)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'RspType' field.
   * @return 响应类型
   */
  public io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRspType getRspType() {
    return RspType;
  }


  /**
   * Sets the value of the 'RspType' field.
   * 响应类型
   * @param value the value to set.
   */
  public FtdcRsp setRspType(io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRspType value) {
    this.RspType = value;
    return this;
  }

  /**
   * Gets the value of the 'epochMillis' field.
   * @return 时间戳
   */
  public long getEpochMillis() {
    return epochMillis;
  }


  /**
   * Sets the value of the 'epochMillis' field.
   * 时间戳
   * @param value the value to set.
   */
  public FtdcRsp setEpochMillis(long value) {
    this.epochMillis = value;
    return this;
  }

  /**
   * Gets the value of the 'RspContent' field.
   * @return FTDC响应内容 - 已序列化
   */
  public java.nio.ByteBuffer getRspContent() {
    return RspContent;
  }


  /**
   * Sets the value of the 'RspContent' field.
   * FTDC响应内容 - 已序列化
   * @param value the value to set.
   */
  public FtdcRsp setRspContent(java.nio.ByteBuffer value) {
    this.RspContent = value;
    return this;
  }

  /**
   * Creates a new FtdcRsp RecordBuilder.
   * @return A new FtdcRsp RecordBuilder
   */
  public static io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRsp.Builder newBuilder() {
    return new io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRsp.Builder();
  }

  /**
   * Creates a new FtdcRsp RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new FtdcRsp RecordBuilder
   */
  public static io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRsp.Builder newBuilder(io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRsp.Builder other) {
    if (other == null) {
      return new io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRsp.Builder();
    } else {
      return new io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRsp.Builder(other);
    }
  }

  /**
   * Creates a new FtdcRsp RecordBuilder by copying an existing FtdcRsp instance.
   * @param other The existing instance to copy.
   * @return A new FtdcRsp RecordBuilder
   */
  public static io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRsp.Builder newBuilder(io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRsp other) {
    if (other == null) {
      return new io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRsp.Builder();
    } else {
      return new io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRsp.Builder(other);
    }
  }

  /**
   * RecordBuilder for FtdcRsp instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<FtdcRsp>
    implements org.apache.avro.data.RecordBuilder<FtdcRsp> {

    /** 响应类型 */
    private io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRspType RspType;
    /** 时间戳 */
    private long epochMillis;
    /** FTDC响应内容 - 已序列化 */
    private java.nio.ByteBuffer RspContent;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRsp.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.RspType)) {
        this.RspType = data().deepCopy(fields()[0].schema(), other.RspType);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.epochMillis)) {
        this.epochMillis = data().deepCopy(fields()[1].schema(), other.epochMillis);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.RspContent)) {
        this.RspContent = data().deepCopy(fields()[2].schema(), other.RspContent);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing FtdcRsp instance
     * @param other The existing instance to copy.
     */
    private Builder(io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRsp other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.RspType)) {
        this.RspType = data().deepCopy(fields()[0].schema(), other.RspType);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.epochMillis)) {
        this.epochMillis = data().deepCopy(fields()[1].schema(), other.epochMillis);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.RspContent)) {
        this.RspContent = data().deepCopy(fields()[2].schema(), other.RspContent);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'RspType' field.
      * 响应类型
      * @return The value.
      */
    public io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRspType getRspType() {
      return RspType;
    }


    /**
      * Sets the value of the 'RspType' field.
      * 响应类型
      * @param value The value of 'RspType'.
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRsp.Builder setRspType(io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRspType value) {
      validate(fields()[0], value);
      this.RspType = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'RspType' field has been set.
      * 响应类型
      * @return True if the 'RspType' field has been set, false otherwise.
      */
    public boolean hasRspType() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'RspType' field.
      * 响应类型
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRsp.Builder clearRspType() {
      RspType = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'epochMillis' field.
      * 时间戳
      * @return The value.
      */
    public long getEpochMillis() {
      return epochMillis;
    }


    /**
      * Sets the value of the 'epochMillis' field.
      * 时间戳
      * @param value The value of 'epochMillis'.
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRsp.Builder setEpochMillis(long value) {
      validate(fields()[1], value);
      this.epochMillis = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'epochMillis' field has been set.
      * 时间戳
      * @return True if the 'epochMillis' field has been set, false otherwise.
      */
    public boolean hasEpochMillis() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'epochMillis' field.
      * 时间戳
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRsp.Builder clearEpochMillis() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'RspContent' field.
      * FTDC响应内容 - 已序列化
      * @return The value.
      */
    public java.nio.ByteBuffer getRspContent() {
      return RspContent;
    }


    /**
      * Sets the value of the 'RspContent' field.
      * FTDC响应内容 - 已序列化
      * @param value The value of 'RspContent'.
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRsp.Builder setRspContent(java.nio.ByteBuffer value) {
      validate(fields()[2], value);
      this.RspContent = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'RspContent' field has been set.
      * FTDC响应内容 - 已序列化
      * @return True if the 'RspContent' field has been set, false otherwise.
      */
    public boolean hasRspContent() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'RspContent' field.
      * FTDC响应内容 - 已序列化
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRsp.Builder clearRspContent() {
      RspContent = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    public FtdcRsp build() {
      try {
        FtdcRsp record = new FtdcRsp();
        record.RspType = fieldSetFlags()[0] ? this.RspType : (io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRspType) defaultValue(fields()[0]);
        record.epochMillis = fieldSetFlags()[1] ? this.epochMillis : (java.lang.Long) defaultValue(fields()[1]);
        record.RspContent = fieldSetFlags()[2] ? this.RspContent : (java.nio.ByteBuffer) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<FtdcRsp>
    WRITER$ = (org.apache.avro.io.DatumWriter<FtdcRsp>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<FtdcRsp>
    READER$ = (org.apache.avro.io.DatumReader<FtdcRsp>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeEnum(this.RspType.ordinal());

    out.writeLong(this.epochMillis);

    out.writeBytes(this.RspContent);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.RspType = io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRspType.values()[in.readEnum()];

      this.epochMillis = in.readLong();

      this.RspContent = in.readBytes(this.RspContent);

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.RspType = io.rapid.adaptor.ctp.serializable.avro.pack.FtdcRspType.values()[in.readEnum()];
          break;

        case 1:
          this.epochMillis = in.readLong();
          break;

        case 2:
          this.RspContent = in.readBytes(this.RspContent);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}









