/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.rapid.core.protocol.avro.request;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

/** 行情订阅多个 */
@org.apache.avro.specific.AvroGenerated
public class MultiMarketDataSubscribe extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 4318669078575820321L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"MultiMarketDataSubscribe\",\"namespace\":\"io.rapid.core.protocol.avro.request\",\"doc\":\"行情订阅多个\",\"fields\":[{\"name\":\"type\",\"type\":{\"type\":\"enum\",\"name\":\"MarketDataType\",\"namespace\":\"io.rapid.core.protocol.avro.enums\",\"doc\":\"行情类型\",\"symbols\":[\"DEPTH\",\"FAST\",\"LEVEL10\",\"LEVEL20\",\"LEVEL30\"]}},{\"name\":\"instrumentCodes\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}},{\"name\":\"recvAddr\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<MultiMarketDataSubscribe> ENCODER =
      new BinaryMessageEncoder<MultiMarketDataSubscribe>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<MultiMarketDataSubscribe> DECODER =
      new BinaryMessageDecoder<MultiMarketDataSubscribe>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<MultiMarketDataSubscribe> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<MultiMarketDataSubscribe> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<MultiMarketDataSubscribe> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<MultiMarketDataSubscribe>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this MultiMarketDataSubscribe to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a MultiMarketDataSubscribe from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a MultiMarketDataSubscribe instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static MultiMarketDataSubscribe fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private io.rapid.core.protocol.avro.enums.MarketDataType type;
  private java.util.List<java.lang.String> instrumentCodes;
  private java.lang.String recvAddr;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public MultiMarketDataSubscribe() {}

  /**
   * All-args constructor.
   * @param type The new value for type
   * @param instrumentCodes The new value for instrumentCodes
   * @param recvAddr The new value for recvAddr
   */
  public MultiMarketDataSubscribe(io.rapid.core.protocol.avro.enums.MarketDataType type, java.util.List<java.lang.String> instrumentCodes, java.lang.String recvAddr) {
    this.type = type;
    this.instrumentCodes = instrumentCodes;
    this.recvAddr = recvAddr;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return type;
    case 1: return instrumentCodes;
    case 2: return recvAddr;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: type = (io.rapid.core.protocol.avro.enums.MarketDataType)value$; break;
    case 1: instrumentCodes = (java.util.List<java.lang.String>)value$; break;
    case 2: recvAddr = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'type' field.
   * @return The value of the 'type' field.
   */
  public io.rapid.core.protocol.avro.enums.MarketDataType getType() {
    return type;
  }


  /**
   * Sets the value of the 'type' field.
   * @param value the value to set.
   */
  public MultiMarketDataSubscribe setType(io.rapid.core.protocol.avro.enums.MarketDataType value) {
    this.type = value;
    return this;
  }

  /**
   * Gets the value of the 'instrumentCodes' field.
   * @return The value of the 'instrumentCodes' field.
   */
  public java.util.List<java.lang.String> getInstrumentCodes() {
    return instrumentCodes;
  }


  /**
   * Sets the value of the 'instrumentCodes' field.
   * @param value the value to set.
   */
  public MultiMarketDataSubscribe setInstrumentCodes(java.util.List<java.lang.String> value) {
    this.instrumentCodes = value;
    return this;
  }

  /**
   * Gets the value of the 'recvAddr' field.
   * @return The value of the 'recvAddr' field.
   */
  public java.lang.String getRecvAddr() {
    return recvAddr;
  }


  /**
   * Sets the value of the 'recvAddr' field.
   * @param value the value to set.
   */
  public MultiMarketDataSubscribe setRecvAddr(java.lang.String value) {
    this.recvAddr = value;
    return this;
  }

  /**
   * Creates a new MultiMarketDataSubscribe RecordBuilder.
   * @return A new MultiMarketDataSubscribe RecordBuilder
   */
  public static io.rapid.core.protocol.avro.request.MultiMarketDataSubscribe.Builder newBuilder() {
    return new io.rapid.core.protocol.avro.request.MultiMarketDataSubscribe.Builder();
  }

  /**
   * Creates a new MultiMarketDataSubscribe RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new MultiMarketDataSubscribe RecordBuilder
   */
  public static io.rapid.core.protocol.avro.request.MultiMarketDataSubscribe.Builder newBuilder(io.rapid.core.protocol.avro.request.MultiMarketDataSubscribe.Builder other) {
    if (other == null) {
      return new io.rapid.core.protocol.avro.request.MultiMarketDataSubscribe.Builder();
    } else {
      return new io.rapid.core.protocol.avro.request.MultiMarketDataSubscribe.Builder(other);
    }
  }

  /**
   * Creates a new MultiMarketDataSubscribe RecordBuilder by copying an existing MultiMarketDataSubscribe instance.
   * @param other The existing instance to copy.
   * @return A new MultiMarketDataSubscribe RecordBuilder
   */
  public static io.rapid.core.protocol.avro.request.MultiMarketDataSubscribe.Builder newBuilder(io.rapid.core.protocol.avro.request.MultiMarketDataSubscribe other) {
    if (other == null) {
      return new io.rapid.core.protocol.avro.request.MultiMarketDataSubscribe.Builder();
    } else {
      return new io.rapid.core.protocol.avro.request.MultiMarketDataSubscribe.Builder(other);
    }
  }

  /**
   * RecordBuilder for MultiMarketDataSubscribe instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<MultiMarketDataSubscribe>
    implements org.apache.avro.data.RecordBuilder<MultiMarketDataSubscribe> {

    private io.rapid.core.protocol.avro.enums.MarketDataType type;
    private java.util.List<java.lang.String> instrumentCodes;
    private java.lang.String recvAddr;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.rapid.core.protocol.avro.request.MultiMarketDataSubscribe.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.type)) {
        this.type = data().deepCopy(fields()[0].schema(), other.type);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.instrumentCodes)) {
        this.instrumentCodes = data().deepCopy(fields()[1].schema(), other.instrumentCodes);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.recvAddr)) {
        this.recvAddr = data().deepCopy(fields()[2].schema(), other.recvAddr);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing MultiMarketDataSubscribe instance
     * @param other The existing instance to copy.
     */
    private Builder(io.rapid.core.protocol.avro.request.MultiMarketDataSubscribe other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.type)) {
        this.type = data().deepCopy(fields()[0].schema(), other.type);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.instrumentCodes)) {
        this.instrumentCodes = data().deepCopy(fields()[1].schema(), other.instrumentCodes);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.recvAddr)) {
        this.recvAddr = data().deepCopy(fields()[2].schema(), other.recvAddr);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'type' field.
      * @return The value.
      */
    public io.rapid.core.protocol.avro.enums.MarketDataType getType() {
      return type;
    }


    /**
      * Sets the value of the 'type' field.
      * @param value The value of 'type'.
      * @return This builder.
      */
    public io.rapid.core.protocol.avro.request.MultiMarketDataSubscribe.Builder setType(io.rapid.core.protocol.avro.enums.MarketDataType value) {
      validate(fields()[0], value);
      this.type = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'type' field has been set.
      * @return True if the 'type' field has been set, false otherwise.
      */
    public boolean hasType() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'type' field.
      * @return This builder.
      */
    public io.rapid.core.protocol.avro.request.MultiMarketDataSubscribe.Builder clearType() {
      type = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'instrumentCodes' field.
      * @return The value.
      */
    public java.util.List<java.lang.String> getInstrumentCodes() {
      return instrumentCodes;
    }


    /**
      * Sets the value of the 'instrumentCodes' field.
      * @param value The value of 'instrumentCodes'.
      * @return This builder.
      */
    public io.rapid.core.protocol.avro.request.MultiMarketDataSubscribe.Builder setInstrumentCodes(java.util.List<java.lang.String> value) {
      validate(fields()[1], value);
      this.instrumentCodes = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'instrumentCodes' field has been set.
      * @return True if the 'instrumentCodes' field has been set, false otherwise.
      */
    public boolean hasInstrumentCodes() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'instrumentCodes' field.
      * @return This builder.
      */
    public io.rapid.core.protocol.avro.request.MultiMarketDataSubscribe.Builder clearInstrumentCodes() {
      instrumentCodes = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'recvAddr' field.
      * @return The value.
      */
    public java.lang.String getRecvAddr() {
      return recvAddr;
    }


    /**
      * Sets the value of the 'recvAddr' field.
      * @param value The value of 'recvAddr'.
      * @return This builder.
      */
    public io.rapid.core.protocol.avro.request.MultiMarketDataSubscribe.Builder setRecvAddr(java.lang.String value) {
      validate(fields()[2], value);
      this.recvAddr = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'recvAddr' field has been set.
      * @return True if the 'recvAddr' field has been set, false otherwise.
      */
    public boolean hasRecvAddr() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'recvAddr' field.
      * @return This builder.
      */
    public io.rapid.core.protocol.avro.request.MultiMarketDataSubscribe.Builder clearRecvAddr() {
      recvAddr = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    public MultiMarketDataSubscribe build() {
      try {
        MultiMarketDataSubscribe record = new MultiMarketDataSubscribe();
        record.type = fieldSetFlags()[0] ? this.type : (io.rapid.core.protocol.avro.enums.MarketDataType) defaultValue(fields()[0]);
        record.instrumentCodes = fieldSetFlags()[1] ? this.instrumentCodes : (java.util.List<java.lang.String>) defaultValue(fields()[1]);
        record.recvAddr = fieldSetFlags()[2] ? this.recvAddr : (java.lang.String) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<MultiMarketDataSubscribe>
    WRITER$ = (org.apache.avro.io.DatumWriter<MultiMarketDataSubscribe>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<MultiMarketDataSubscribe>
    READER$ = (org.apache.avro.io.DatumReader<MultiMarketDataSubscribe>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeEnum(this.type.ordinal());

    long size0 = this.instrumentCodes.size();
    out.writeArrayStart();
    out.setItemCount(size0);
    long actualSize0 = 0;
    for (java.lang.String e0: this.instrumentCodes) {
      actualSize0++;
      out.startItem();
      out.writeString(e0);
    }
    out.writeArrayEnd();
    if (actualSize0 != size0)
      throw new java.util.ConcurrentModificationException("Array-size written was " + size0 + ", but element count was " + actualSize0 + ".");

    out.writeString(this.recvAddr);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.type = io.rapid.core.protocol.avro.enums.MarketDataType.values()[in.readEnum()];

      long size0 = in.readArrayStart();
      java.util.List<java.lang.String> a0 = this.instrumentCodes;
      if (a0 == null) {
        a0 = new SpecificData.Array<java.lang.String>((int)size0, SCHEMA$.getField("instrumentCodes").schema());
        this.instrumentCodes = a0;
      } else a0.clear();
      SpecificData.Array<java.lang.String> ga0 = (a0 instanceof SpecificData.Array ? (SpecificData.Array<java.lang.String>)a0 : null);
      for ( ; 0 < size0; size0 = in.arrayNext()) {
        for ( ; size0 != 0; size0--) {
          java.lang.String e0 = (ga0 != null ? ga0.peek() : null);
          e0 = in.readString();
          a0.add(e0);
        }
      }

      this.recvAddr = in.readString();

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.type = io.rapid.core.protocol.avro.enums.MarketDataType.values()[in.readEnum()];
          break;

        case 1:
          long size0 = in.readArrayStart();
          java.util.List<java.lang.String> a0 = this.instrumentCodes;
          if (a0 == null) {
            a0 = new SpecificData.Array<java.lang.String>((int)size0, SCHEMA$.getField("instrumentCodes").schema());
            this.instrumentCodes = a0;
          } else a0.clear();
          SpecificData.Array<java.lang.String> ga0 = (a0 instanceof SpecificData.Array ? (SpecificData.Array<java.lang.String>)a0 : null);
          for ( ; 0 < size0; size0 = in.arrayNext()) {
            for ( ; size0 != 0; size0--) {
              java.lang.String e0 = (ga0 != null ? ga0.peek() : null);
              e0 = in.readString();
              a0.add(e0);
            }
          }
          break;

        case 2:
          this.recvAddr = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}









