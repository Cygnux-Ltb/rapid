/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.rapid.core.serializable.avro.outbound;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

/** 查询账户余额 */
@org.apache.avro.specific.AvroGenerated
public class QueryBalance extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -8753488993617999489L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"QueryBalance\",\"namespace\":\"io.rapid.core.serializable.avro.outbound\",\"doc\":\"查询账户余额\",\"fields\":[{\"name\":\"generateTime\",\"type\":\"long\",\"default\":0},{\"name\":\"sendTime\",\"type\":\"long\",\"default\":0},{\"name\":\"accountId\",\"type\":\"int\"},{\"name\":\"brokerId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"subAccountId\",\"type\":\"int\"},{\"name\":\"strategyId\",\"type\":\"int\"},{\"name\":\"operatorId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"default\":\"\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<QueryBalance> ENCODER =
      new BinaryMessageEncoder<QueryBalance>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<QueryBalance> DECODER =
      new BinaryMessageDecoder<QueryBalance>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<QueryBalance> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<QueryBalance> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<QueryBalance> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<QueryBalance>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this QueryBalance to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a QueryBalance from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a QueryBalance instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static QueryBalance fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private long generateTime;
  private long sendTime;
  private int accountId;
  private java.lang.String brokerId;
  private int subAccountId;
  private int strategyId;
  private java.lang.String operatorId;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public QueryBalance() {}

  /**
   * All-args constructor.
   * @param generateTime The new value for generateTime
   * @param sendTime The new value for sendTime
   * @param accountId The new value for accountId
   * @param brokerId The new value for brokerId
   * @param subAccountId The new value for subAccountId
   * @param strategyId The new value for strategyId
   * @param operatorId The new value for operatorId
   */
  public QueryBalance(java.lang.Long generateTime, java.lang.Long sendTime, java.lang.Integer accountId, java.lang.String brokerId, java.lang.Integer subAccountId, java.lang.Integer strategyId, java.lang.String operatorId) {
    this.generateTime = generateTime;
    this.sendTime = sendTime;
    this.accountId = accountId;
    this.brokerId = brokerId;
    this.subAccountId = subAccountId;
    this.strategyId = strategyId;
    this.operatorId = operatorId;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return generateTime;
    case 1: return sendTime;
    case 2: return accountId;
    case 3: return brokerId;
    case 4: return subAccountId;
    case 5: return strategyId;
    case 6: return operatorId;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: generateTime = (java.lang.Long)value$; break;
    case 1: sendTime = (java.lang.Long)value$; break;
    case 2: accountId = (java.lang.Integer)value$; break;
    case 3: brokerId = value$ != null ? value$.toString() : null; break;
    case 4: subAccountId = (java.lang.Integer)value$; break;
    case 5: strategyId = (java.lang.Integer)value$; break;
    case 6: operatorId = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'generateTime' field.
   * @return The value of the 'generateTime' field.
   */
  public long getGenerateTime() {
    return generateTime;
  }


  /**
   * Sets the value of the 'generateTime' field.
   * @param value the value to set.
   */
  public QueryBalance setGenerateTime(long value) {
    this.generateTime = value;
    return this;
  }

  /**
   * Gets the value of the 'sendTime' field.
   * @return The value of the 'sendTime' field.
   */
  public long getSendTime() {
    return sendTime;
  }


  /**
   * Sets the value of the 'sendTime' field.
   * @param value the value to set.
   */
  public QueryBalance setSendTime(long value) {
    this.sendTime = value;
    return this;
  }

  /**
   * Gets the value of the 'accountId' field.
   * @return The value of the 'accountId' field.
   */
  public int getAccountId() {
    return accountId;
  }


  /**
   * Sets the value of the 'accountId' field.
   * @param value the value to set.
   */
  public QueryBalance setAccountId(int value) {
    this.accountId = value;
    return this;
  }

  /**
   * Gets the value of the 'brokerId' field.
   * @return The value of the 'brokerId' field.
   */
  public java.lang.String getBrokerId() {
    return brokerId;
  }


  /**
   * Sets the value of the 'brokerId' field.
   * @param value the value to set.
   */
  public QueryBalance setBrokerId(java.lang.String value) {
    this.brokerId = value;
    return this;
  }

  /**
   * Gets the value of the 'subAccountId' field.
   * @return The value of the 'subAccountId' field.
   */
  public int getSubAccountId() {
    return subAccountId;
  }


  /**
   * Sets the value of the 'subAccountId' field.
   * @param value the value to set.
   */
  public QueryBalance setSubAccountId(int value) {
    this.subAccountId = value;
    return this;
  }

  /**
   * Gets the value of the 'strategyId' field.
   * @return The value of the 'strategyId' field.
   */
  public int getStrategyId() {
    return strategyId;
  }


  /**
   * Sets the value of the 'strategyId' field.
   * @param value the value to set.
   */
  public QueryBalance setStrategyId(int value) {
    this.strategyId = value;
    return this;
  }

  /**
   * Gets the value of the 'operatorId' field.
   * @return The value of the 'operatorId' field.
   */
  public java.lang.String getOperatorId() {
    return operatorId;
  }


  /**
   * Sets the value of the 'operatorId' field.
   * @param value the value to set.
   */
  public QueryBalance setOperatorId(java.lang.String value) {
    this.operatorId = value;
    return this;
  }

  /**
   * Creates a new QueryBalance RecordBuilder.
   * @return A new QueryBalance RecordBuilder
   */
  public static io.rapid.core.serializable.avro.outbound.QueryBalance.Builder newBuilder() {
    return new io.rapid.core.serializable.avro.outbound.QueryBalance.Builder();
  }

  /**
   * Creates a new QueryBalance RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new QueryBalance RecordBuilder
   */
  public static io.rapid.core.serializable.avro.outbound.QueryBalance.Builder newBuilder(io.rapid.core.serializable.avro.outbound.QueryBalance.Builder other) {
    if (other == null) {
      return new io.rapid.core.serializable.avro.outbound.QueryBalance.Builder();
    } else {
      return new io.rapid.core.serializable.avro.outbound.QueryBalance.Builder(other);
    }
  }

  /**
   * Creates a new QueryBalance RecordBuilder by copying an existing QueryBalance instance.
   * @param other The existing instance to copy.
   * @return A new QueryBalance RecordBuilder
   */
  public static io.rapid.core.serializable.avro.outbound.QueryBalance.Builder newBuilder(io.rapid.core.serializable.avro.outbound.QueryBalance other) {
    if (other == null) {
      return new io.rapid.core.serializable.avro.outbound.QueryBalance.Builder();
    } else {
      return new io.rapid.core.serializable.avro.outbound.QueryBalance.Builder(other);
    }
  }

  /**
   * RecordBuilder for QueryBalance instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<QueryBalance>
    implements org.apache.avro.data.RecordBuilder<QueryBalance> {

    private long generateTime;
    private long sendTime;
    private int accountId;
    private java.lang.String brokerId;
    private int subAccountId;
    private int strategyId;
    private java.lang.String operatorId;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.rapid.core.serializable.avro.outbound.QueryBalance.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.generateTime)) {
        this.generateTime = data().deepCopy(fields()[0].schema(), other.generateTime);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.sendTime)) {
        this.sendTime = data().deepCopy(fields()[1].schema(), other.sendTime);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.accountId)) {
        this.accountId = data().deepCopy(fields()[2].schema(), other.accountId);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.brokerId)) {
        this.brokerId = data().deepCopy(fields()[3].schema(), other.brokerId);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.subAccountId)) {
        this.subAccountId = data().deepCopy(fields()[4].schema(), other.subAccountId);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.strategyId)) {
        this.strategyId = data().deepCopy(fields()[5].schema(), other.strategyId);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.operatorId)) {
        this.operatorId = data().deepCopy(fields()[6].schema(), other.operatorId);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
    }

    /**
     * Creates a Builder by copying an existing QueryBalance instance
     * @param other The existing instance to copy.
     */
    private Builder(io.rapid.core.serializable.avro.outbound.QueryBalance other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.generateTime)) {
        this.generateTime = data().deepCopy(fields()[0].schema(), other.generateTime);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.sendTime)) {
        this.sendTime = data().deepCopy(fields()[1].schema(), other.sendTime);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.accountId)) {
        this.accountId = data().deepCopy(fields()[2].schema(), other.accountId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.brokerId)) {
        this.brokerId = data().deepCopy(fields()[3].schema(), other.brokerId);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.subAccountId)) {
        this.subAccountId = data().deepCopy(fields()[4].schema(), other.subAccountId);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.strategyId)) {
        this.strategyId = data().deepCopy(fields()[5].schema(), other.strategyId);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.operatorId)) {
        this.operatorId = data().deepCopy(fields()[6].schema(), other.operatorId);
        fieldSetFlags()[6] = true;
      }
    }

    /**
      * Gets the value of the 'generateTime' field.
      * @return The value.
      */
    public long getGenerateTime() {
      return generateTime;
    }


    /**
      * Sets the value of the 'generateTime' field.
      * @param value The value of 'generateTime'.
      * @return This builder.
      */
    public io.rapid.core.serializable.avro.outbound.QueryBalance.Builder setGenerateTime(long value) {
      validate(fields()[0], value);
      this.generateTime = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'generateTime' field has been set.
      * @return True if the 'generateTime' field has been set, false otherwise.
      */
    public boolean hasGenerateTime() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'generateTime' field.
      * @return This builder.
      */
    public io.rapid.core.serializable.avro.outbound.QueryBalance.Builder clearGenerateTime() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'sendTime' field.
      * @return The value.
      */
    public long getSendTime() {
      return sendTime;
    }


    /**
      * Sets the value of the 'sendTime' field.
      * @param value The value of 'sendTime'.
      * @return This builder.
      */
    public io.rapid.core.serializable.avro.outbound.QueryBalance.Builder setSendTime(long value) {
      validate(fields()[1], value);
      this.sendTime = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'sendTime' field has been set.
      * @return True if the 'sendTime' field has been set, false otherwise.
      */
    public boolean hasSendTime() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'sendTime' field.
      * @return This builder.
      */
    public io.rapid.core.serializable.avro.outbound.QueryBalance.Builder clearSendTime() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'accountId' field.
      * @return The value.
      */
    public int getAccountId() {
      return accountId;
    }


    /**
      * Sets the value of the 'accountId' field.
      * @param value The value of 'accountId'.
      * @return This builder.
      */
    public io.rapid.core.serializable.avro.outbound.QueryBalance.Builder setAccountId(int value) {
      validate(fields()[2], value);
      this.accountId = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'accountId' field has been set.
      * @return True if the 'accountId' field has been set, false otherwise.
      */
    public boolean hasAccountId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'accountId' field.
      * @return This builder.
      */
    public io.rapid.core.serializable.avro.outbound.QueryBalance.Builder clearAccountId() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'brokerId' field.
      * @return The value.
      */
    public java.lang.String getBrokerId() {
      return brokerId;
    }


    /**
      * Sets the value of the 'brokerId' field.
      * @param value The value of 'brokerId'.
      * @return This builder.
      */
    public io.rapid.core.serializable.avro.outbound.QueryBalance.Builder setBrokerId(java.lang.String value) {
      validate(fields()[3], value);
      this.brokerId = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'brokerId' field has been set.
      * @return True if the 'brokerId' field has been set, false otherwise.
      */
    public boolean hasBrokerId() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'brokerId' field.
      * @return This builder.
      */
    public io.rapid.core.serializable.avro.outbound.QueryBalance.Builder clearBrokerId() {
      brokerId = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'subAccountId' field.
      * @return The value.
      */
    public int getSubAccountId() {
      return subAccountId;
    }


    /**
      * Sets the value of the 'subAccountId' field.
      * @param value The value of 'subAccountId'.
      * @return This builder.
      */
    public io.rapid.core.serializable.avro.outbound.QueryBalance.Builder setSubAccountId(int value) {
      validate(fields()[4], value);
      this.subAccountId = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'subAccountId' field has been set.
      * @return True if the 'subAccountId' field has been set, false otherwise.
      */
    public boolean hasSubAccountId() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'subAccountId' field.
      * @return This builder.
      */
    public io.rapid.core.serializable.avro.outbound.QueryBalance.Builder clearSubAccountId() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'strategyId' field.
      * @return The value.
      */
    public int getStrategyId() {
      return strategyId;
    }


    /**
      * Sets the value of the 'strategyId' field.
      * @param value The value of 'strategyId'.
      * @return This builder.
      */
    public io.rapid.core.serializable.avro.outbound.QueryBalance.Builder setStrategyId(int value) {
      validate(fields()[5], value);
      this.strategyId = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'strategyId' field has been set.
      * @return True if the 'strategyId' field has been set, false otherwise.
      */
    public boolean hasStrategyId() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'strategyId' field.
      * @return This builder.
      */
    public io.rapid.core.serializable.avro.outbound.QueryBalance.Builder clearStrategyId() {
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'operatorId' field.
      * @return The value.
      */
    public java.lang.String getOperatorId() {
      return operatorId;
    }


    /**
      * Sets the value of the 'operatorId' field.
      * @param value The value of 'operatorId'.
      * @return This builder.
      */
    public io.rapid.core.serializable.avro.outbound.QueryBalance.Builder setOperatorId(java.lang.String value) {
      validate(fields()[6], value);
      this.operatorId = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'operatorId' field has been set.
      * @return True if the 'operatorId' field has been set, false otherwise.
      */
    public boolean hasOperatorId() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'operatorId' field.
      * @return This builder.
      */
    public io.rapid.core.serializable.avro.outbound.QueryBalance.Builder clearOperatorId() {
      operatorId = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    public QueryBalance build() {
      try {
        QueryBalance record = new QueryBalance();
        record.generateTime = fieldSetFlags()[0] ? this.generateTime : (java.lang.Long) defaultValue(fields()[0]);
        record.sendTime = fieldSetFlags()[1] ? this.sendTime : (java.lang.Long) defaultValue(fields()[1]);
        record.accountId = fieldSetFlags()[2] ? this.accountId : (java.lang.Integer) defaultValue(fields()[2]);
        record.brokerId = fieldSetFlags()[3] ? this.brokerId : (java.lang.String) defaultValue(fields()[3]);
        record.subAccountId = fieldSetFlags()[4] ? this.subAccountId : (java.lang.Integer) defaultValue(fields()[4]);
        record.strategyId = fieldSetFlags()[5] ? this.strategyId : (java.lang.Integer) defaultValue(fields()[5]);
        record.operatorId = fieldSetFlags()[6] ? this.operatorId : (java.lang.String) defaultValue(fields()[6]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<QueryBalance>
    WRITER$ = (org.apache.avro.io.DatumWriter<QueryBalance>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<QueryBalance>
    READER$ = (org.apache.avro.io.DatumReader<QueryBalance>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeLong(this.generateTime);

    out.writeLong(this.sendTime);

    out.writeInt(this.accountId);

    out.writeString(this.brokerId);

    out.writeInt(this.subAccountId);

    out.writeInt(this.strategyId);

    out.writeString(this.operatorId);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.generateTime = in.readLong();

      this.sendTime = in.readLong();

      this.accountId = in.readInt();

      this.brokerId = in.readString();

      this.subAccountId = in.readInt();

      this.strategyId = in.readInt();

      this.operatorId = in.readString();

    } else {
      for (int i = 0; i < 7; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.generateTime = in.readLong();
          break;

        case 1:
          this.sendTime = in.readLong();
          break;

        case 2:
          this.accountId = in.readInt();
          break;

        case 3:
          this.brokerId = in.readString();
          break;

        case 4:
          this.subAccountId = in.readInt();
          break;

        case 5:
          this.strategyId = in.readInt();
          break;

        case 6:
          this.operatorId = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










