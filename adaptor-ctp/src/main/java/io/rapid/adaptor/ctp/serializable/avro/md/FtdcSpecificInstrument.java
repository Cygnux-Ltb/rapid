/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.rapid.adaptor.ctp.serializable.avro.md;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

/** 指定的合约 */
@org.apache.avro.specific.AvroGenerated
public class FtdcSpecificInstrument extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -2163977776106870243L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"FtdcSpecificInstrument\",\"namespace\":\"io.rapid.adaptor.ctp.serializable.avro.md\",\"doc\":\"指定的合约\",\"fields\":[{\"name\":\"Source\",\"type\":{\"type\":\"enum\",\"name\":\"SpecificInstrumentSource\",\"doc\":\"SpecificInstrument 来源\",\"symbols\":[\"SubMarketData\",\"UnsubMarketData\",\"SubForQuoteRsp\",\"UnsubForQuoteRsp\"]},\"doc\":\"SpecificInstrument Source\"},{\"name\":\"ErrorID\",\"type\":\"int\",\"doc\":\"FTDC响应信息 - 错误代码\"},{\"name\":\"ErrorMsg\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"FTDC响应信息 - 错误信息\"},{\"name\":\"RequestID\",\"type\":\"int\",\"doc\":\"请求ID\"},{\"name\":\"IsLast\",\"type\":\"boolean\",\"doc\":\"是否最后一条信息\"},{\"name\":\"InstrumentID\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Instrument ID\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<FtdcSpecificInstrument> ENCODER =
      new BinaryMessageEncoder<FtdcSpecificInstrument>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<FtdcSpecificInstrument> DECODER =
      new BinaryMessageDecoder<FtdcSpecificInstrument>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<FtdcSpecificInstrument> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<FtdcSpecificInstrument> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<FtdcSpecificInstrument> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<FtdcSpecificInstrument>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this FtdcSpecificInstrument to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a FtdcSpecificInstrument from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a FtdcSpecificInstrument instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static FtdcSpecificInstrument fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** SpecificInstrument Source */
  public io.rapid.adaptor.ctp.serializable.avro.md.SpecificInstrumentSource Source;
  /** FTDC响应信息 - 错误代码 */
  public int ErrorID;
  /** FTDC响应信息 - 错误信息 */
  public java.lang.String ErrorMsg;
  /** 请求ID */
  public int RequestID;
  /** 是否最后一条信息 */
  public boolean IsLast;
  /** Instrument ID */
  public java.lang.String InstrumentID;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public FtdcSpecificInstrument() {}

  /**
   * All-args constructor.
   * @param Source SpecificInstrument Source
   * @param ErrorID FTDC响应信息 - 错误代码
   * @param ErrorMsg FTDC响应信息 - 错误信息
   * @param RequestID 请求ID
   * @param IsLast 是否最后一条信息
   * @param InstrumentID Instrument ID
   */
  public FtdcSpecificInstrument(io.rapid.adaptor.ctp.serializable.avro.md.SpecificInstrumentSource Source, java.lang.Integer ErrorID, java.lang.String ErrorMsg, java.lang.Integer RequestID, java.lang.Boolean IsLast, java.lang.String InstrumentID) {
    this.Source = Source;
    this.ErrorID = ErrorID;
    this.ErrorMsg = ErrorMsg;
    this.RequestID = RequestID;
    this.IsLast = IsLast;
    this.InstrumentID = InstrumentID;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return Source;
    case 1: return ErrorID;
    case 2: return ErrorMsg;
    case 3: return RequestID;
    case 4: return IsLast;
    case 5: return InstrumentID;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: Source = (io.rapid.adaptor.ctp.serializable.avro.md.SpecificInstrumentSource)value$; break;
    case 1: ErrorID = (java.lang.Integer)value$; break;
    case 2: ErrorMsg = value$ != null ? value$.toString() : null; break;
    case 3: RequestID = (java.lang.Integer)value$; break;
    case 4: IsLast = (java.lang.Boolean)value$; break;
    case 5: InstrumentID = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'Source' field.
   * @return SpecificInstrument Source
   */
  public io.rapid.adaptor.ctp.serializable.avro.md.SpecificInstrumentSource getSource() {
    return Source;
  }


  /**
   * Sets the value of the 'Source' field.
   * SpecificInstrument Source
   * @param value the value to set.
   */
  public FtdcSpecificInstrument setSource(io.rapid.adaptor.ctp.serializable.avro.md.SpecificInstrumentSource value) {
    this.Source = value;
    return this;
  }

  /**
   * Gets the value of the 'ErrorID' field.
   * @return FTDC响应信息 - 错误代码
   */
  public int getErrorID() {
    return ErrorID;
  }


  /**
   * Sets the value of the 'ErrorID' field.
   * FTDC响应信息 - 错误代码
   * @param value the value to set.
   */
  public FtdcSpecificInstrument setErrorID(int value) {
    this.ErrorID = value;
    return this;
  }

  /**
   * Gets the value of the 'ErrorMsg' field.
   * @return FTDC响应信息 - 错误信息
   */
  public java.lang.String getErrorMsg() {
    return ErrorMsg;
  }


  /**
   * Sets the value of the 'ErrorMsg' field.
   * FTDC响应信息 - 错误信息
   * @param value the value to set.
   */
  public FtdcSpecificInstrument setErrorMsg(java.lang.String value) {
    this.ErrorMsg = value;
    return this;
  }

  /**
   * Gets the value of the 'RequestID' field.
   * @return 请求ID
   */
  public int getRequestID() {
    return RequestID;
  }


  /**
   * Sets the value of the 'RequestID' field.
   * 请求ID
   * @param value the value to set.
   */
  public FtdcSpecificInstrument setRequestID(int value) {
    this.RequestID = value;
    return this;
  }

  /**
   * Gets the value of the 'IsLast' field.
   * @return 是否最后一条信息
   */
  public boolean getIsLast() {
    return IsLast;
  }


  /**
   * Sets the value of the 'IsLast' field.
   * 是否最后一条信息
   * @param value the value to set.
   */
  public FtdcSpecificInstrument setIsLast(boolean value) {
    this.IsLast = value;
    return this;
  }

  /**
   * Gets the value of the 'InstrumentID' field.
   * @return Instrument ID
   */
  public java.lang.String getInstrumentID() {
    return InstrumentID;
  }


  /**
   * Sets the value of the 'InstrumentID' field.
   * Instrument ID
   * @param value the value to set.
   */
  public FtdcSpecificInstrument setInstrumentID(java.lang.String value) {
    this.InstrumentID = value;
    return this;
  }

  /**
   * Creates a new FtdcSpecificInstrument RecordBuilder.
   * @return A new FtdcSpecificInstrument RecordBuilder
   */
  public static io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder newBuilder() {
    return new io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder();
  }

  /**
   * Creates a new FtdcSpecificInstrument RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new FtdcSpecificInstrument RecordBuilder
   */
  public static io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder newBuilder(io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder other) {
    if (other == null) {
      return new io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder();
    } else {
      return new io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder(other);
    }
  }

  /**
   * Creates a new FtdcSpecificInstrument RecordBuilder by copying an existing FtdcSpecificInstrument instance.
   * @param other The existing instance to copy.
   * @return A new FtdcSpecificInstrument RecordBuilder
   */
  public static io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder newBuilder(io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument other) {
    if (other == null) {
      return new io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder();
    } else {
      return new io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder(other);
    }
  }

  /**
   * RecordBuilder for FtdcSpecificInstrument instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<FtdcSpecificInstrument>
    implements org.apache.avro.data.RecordBuilder<FtdcSpecificInstrument> {

    /** SpecificInstrument Source */
    private io.rapid.adaptor.ctp.serializable.avro.md.SpecificInstrumentSource Source;
    /** FTDC响应信息 - 错误代码 */
    private int ErrorID;
    /** FTDC响应信息 - 错误信息 */
    private java.lang.String ErrorMsg;
    /** 请求ID */
    private int RequestID;
    /** 是否最后一条信息 */
    private boolean IsLast;
    /** Instrument ID */
    private java.lang.String InstrumentID;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.Source)) {
        this.Source = data().deepCopy(fields()[0].schema(), other.Source);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.ErrorID)) {
        this.ErrorID = data().deepCopy(fields()[1].schema(), other.ErrorID);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.ErrorMsg)) {
        this.ErrorMsg = data().deepCopy(fields()[2].schema(), other.ErrorMsg);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.RequestID)) {
        this.RequestID = data().deepCopy(fields()[3].schema(), other.RequestID);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.IsLast)) {
        this.IsLast = data().deepCopy(fields()[4].schema(), other.IsLast);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.InstrumentID)) {
        this.InstrumentID = data().deepCopy(fields()[5].schema(), other.InstrumentID);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
    }

    /**
     * Creates a Builder by copying an existing FtdcSpecificInstrument instance
     * @param other The existing instance to copy.
     */
    private Builder(io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.Source)) {
        this.Source = data().deepCopy(fields()[0].schema(), other.Source);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.ErrorID)) {
        this.ErrorID = data().deepCopy(fields()[1].schema(), other.ErrorID);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.ErrorMsg)) {
        this.ErrorMsg = data().deepCopy(fields()[2].schema(), other.ErrorMsg);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.RequestID)) {
        this.RequestID = data().deepCopy(fields()[3].schema(), other.RequestID);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.IsLast)) {
        this.IsLast = data().deepCopy(fields()[4].schema(), other.IsLast);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.InstrumentID)) {
        this.InstrumentID = data().deepCopy(fields()[5].schema(), other.InstrumentID);
        fieldSetFlags()[5] = true;
      }
    }

    /**
      * Gets the value of the 'Source' field.
      * SpecificInstrument Source
      * @return The value.
      */
    public io.rapid.adaptor.ctp.serializable.avro.md.SpecificInstrumentSource getSource() {
      return Source;
    }


    /**
      * Sets the value of the 'Source' field.
      * SpecificInstrument Source
      * @param value The value of 'Source'.
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder setSource(io.rapid.adaptor.ctp.serializable.avro.md.SpecificInstrumentSource value) {
      validate(fields()[0], value);
      this.Source = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'Source' field has been set.
      * SpecificInstrument Source
      * @return True if the 'Source' field has been set, false otherwise.
      */
    public boolean hasSource() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'Source' field.
      * SpecificInstrument Source
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder clearSource() {
      Source = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'ErrorID' field.
      * FTDC响应信息 - 错误代码
      * @return The value.
      */
    public int getErrorID() {
      return ErrorID;
    }


    /**
      * Sets the value of the 'ErrorID' field.
      * FTDC响应信息 - 错误代码
      * @param value The value of 'ErrorID'.
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder setErrorID(int value) {
      validate(fields()[1], value);
      this.ErrorID = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'ErrorID' field has been set.
      * FTDC响应信息 - 错误代码
      * @return True if the 'ErrorID' field has been set, false otherwise.
      */
    public boolean hasErrorID() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'ErrorID' field.
      * FTDC响应信息 - 错误代码
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder clearErrorID() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'ErrorMsg' field.
      * FTDC响应信息 - 错误信息
      * @return The value.
      */
    public java.lang.String getErrorMsg() {
      return ErrorMsg;
    }


    /**
      * Sets the value of the 'ErrorMsg' field.
      * FTDC响应信息 - 错误信息
      * @param value The value of 'ErrorMsg'.
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder setErrorMsg(java.lang.String value) {
      validate(fields()[2], value);
      this.ErrorMsg = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'ErrorMsg' field has been set.
      * FTDC响应信息 - 错误信息
      * @return True if the 'ErrorMsg' field has been set, false otherwise.
      */
    public boolean hasErrorMsg() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'ErrorMsg' field.
      * FTDC响应信息 - 错误信息
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder clearErrorMsg() {
      ErrorMsg = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'RequestID' field.
      * 请求ID
      * @return The value.
      */
    public int getRequestID() {
      return RequestID;
    }


    /**
      * Sets the value of the 'RequestID' field.
      * 请求ID
      * @param value The value of 'RequestID'.
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder setRequestID(int value) {
      validate(fields()[3], value);
      this.RequestID = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'RequestID' field has been set.
      * 请求ID
      * @return True if the 'RequestID' field has been set, false otherwise.
      */
    public boolean hasRequestID() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'RequestID' field.
      * 请求ID
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder clearRequestID() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'IsLast' field.
      * 是否最后一条信息
      * @return The value.
      */
    public boolean getIsLast() {
      return IsLast;
    }


    /**
      * Sets the value of the 'IsLast' field.
      * 是否最后一条信息
      * @param value The value of 'IsLast'.
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder setIsLast(boolean value) {
      validate(fields()[4], value);
      this.IsLast = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'IsLast' field has been set.
      * 是否最后一条信息
      * @return True if the 'IsLast' field has been set, false otherwise.
      */
    public boolean hasIsLast() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'IsLast' field.
      * 是否最后一条信息
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder clearIsLast() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'InstrumentID' field.
      * Instrument ID
      * @return The value.
      */
    public java.lang.String getInstrumentID() {
      return InstrumentID;
    }


    /**
      * Sets the value of the 'InstrumentID' field.
      * Instrument ID
      * @param value The value of 'InstrumentID'.
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder setInstrumentID(java.lang.String value) {
      validate(fields()[5], value);
      this.InstrumentID = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'InstrumentID' field has been set.
      * Instrument ID
      * @return True if the 'InstrumentID' field has been set, false otherwise.
      */
    public boolean hasInstrumentID() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'InstrumentID' field.
      * Instrument ID
      * @return This builder.
      */
    public io.rapid.adaptor.ctp.serializable.avro.md.FtdcSpecificInstrument.Builder clearInstrumentID() {
      InstrumentID = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    @Override
    public FtdcSpecificInstrument build() {
      try {
        FtdcSpecificInstrument record = new FtdcSpecificInstrument();
        record.Source = fieldSetFlags()[0] ? this.Source : (io.rapid.adaptor.ctp.serializable.avro.md.SpecificInstrumentSource) defaultValue(fields()[0]);
        record.ErrorID = fieldSetFlags()[1] ? this.ErrorID : (java.lang.Integer) defaultValue(fields()[1]);
        record.ErrorMsg = fieldSetFlags()[2] ? this.ErrorMsg : (java.lang.String) defaultValue(fields()[2]);
        record.RequestID = fieldSetFlags()[3] ? this.RequestID : (java.lang.Integer) defaultValue(fields()[3]);
        record.IsLast = fieldSetFlags()[4] ? this.IsLast : (java.lang.Boolean) defaultValue(fields()[4]);
        record.InstrumentID = fieldSetFlags()[5] ? this.InstrumentID : (java.lang.String) defaultValue(fields()[5]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<FtdcSpecificInstrument>
    WRITER$ = (org.apache.avro.io.DatumWriter<FtdcSpecificInstrument>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<FtdcSpecificInstrument>
    READER$ = (org.apache.avro.io.DatumReader<FtdcSpecificInstrument>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeEnum(this.Source.ordinal());

    out.writeInt(this.ErrorID);

    out.writeString(this.ErrorMsg);

    out.writeInt(this.RequestID);

    out.writeBoolean(this.IsLast);

    out.writeString(this.InstrumentID);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.Source = io.rapid.adaptor.ctp.serializable.avro.md.SpecificInstrumentSource.values()[in.readEnum()];

      this.ErrorID = in.readInt();

      this.ErrorMsg = in.readString();

      this.RequestID = in.readInt();

      this.IsLast = in.readBoolean();

      this.InstrumentID = in.readString();

    } else {
      for (int i = 0; i < 6; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.Source = io.rapid.adaptor.ctp.serializable.avro.md.SpecificInstrumentSource.values()[in.readEnum()];
          break;

        case 1:
          this.ErrorID = in.readInt();
          break;

        case 2:
          this.ErrorMsg = in.readString();
          break;

        case 3:
          this.RequestID = in.readInt();
          break;

        case 4:
          this.IsLast = in.readBoolean();
          break;

        case 5:
          this.InstrumentID = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










