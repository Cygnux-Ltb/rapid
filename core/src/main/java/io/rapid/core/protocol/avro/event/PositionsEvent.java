/**
 * Autogenerated by Avro
 * <p>
 * DO NOT EDIT DIRECTLY
 */
package io.rapid.core.protocol.avro.event;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;

/**
 * 持仓回报
 */
@org.apache.avro.specific.AvroGenerated
public class PositionsEvent extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
    private static final long serialVersionUID = 3844398210339338348L;


    public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PositionsEvent\",\"namespace\":\"io.rapid.core.protocol.avro.event\",\"doc\":\"持仓回报\",\"fields\":[{\"name\":\"epochMillis\",\"type\":\"long\"},{\"name\":\"brokerId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"investorId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"exchangeCode\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"instrumentCode\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"qty\",\"type\":\"int\"}]}");

    public static org.apache.avro.Schema getClassSchema() {
        return SCHEMA$;
    }

    private static final SpecificData MODEL$ = new SpecificData();

    private static final BinaryMessageEncoder<PositionsEvent> ENCODER =
            new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

    private static final BinaryMessageDecoder<PositionsEvent> DECODER =
            new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

    /**
     * Return the BinaryMessageEncoder instance used by this class.
     *
     * @return the message encoder used by this class
     */
    public static BinaryMessageEncoder<PositionsEvent> getEncoder() {
        return ENCODER;
    }

    /**
     * Return the BinaryMessageDecoder instance used by this class.
     *
     * @return the message decoder used by this class
     */
    public static BinaryMessageDecoder<PositionsEvent> getDecoder() {
        return DECODER;
    }

    /**
     * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
     *
     * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
     * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
     */
    public static BinaryMessageDecoder<PositionsEvent> createDecoder(SchemaStore resolver) {
        return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
    }

    /**
     * Serializes this PositionsEvent to a ByteBuffer.
     *
     * @return a buffer holding the serialized data for this instance
     * @throws java.io.IOException if this instance could not be serialized
     */
    public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
        return ENCODER.encode(this);
    }

    /**
     * Deserializes a PositionsEvent from a ByteBuffer.
     *
     * @param b a byte buffer holding serialized data for an instance of this class
     * @return a PositionsEvent instance decoded from the given buffer
     * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
     */
    public static PositionsEvent fromByteBuffer(
            java.nio.ByteBuffer b) throws java.io.IOException {
        return DECODER.decode(b);
    }

    private long epochMillis;
    private java.lang.String brokerId;
    private java.lang.String investorId;
    private java.lang.String exchangeCode;
    private java.lang.String instrumentCode;
    private int qty;

    /**
     * Default constructor.  Note that this does not initialize fields
     * to their default values from the schema.  If that is desired then
     * one should use <code>newBuilder()</code>.
     */
    public PositionsEvent() {
    }

    /**
     * All-args constructor.
     *
     * @param epochMillis    The new value for epochMillis
     * @param brokerId       The new value for brokerId
     * @param investorId     The new value for investorId
     * @param exchangeCode   The new value for exchangeCode
     * @param instrumentCode The new value for instrumentCode
     * @param qty            The new value for qty
     */
    public PositionsEvent(java.lang.Long epochMillis, java.lang.String brokerId, java.lang.String investorId, java.lang.String exchangeCode, java.lang.String instrumentCode, java.lang.Integer qty) {
        this.epochMillis = epochMillis;
        this.brokerId = brokerId;
        this.investorId = investorId;
        this.exchangeCode = exchangeCode;
        this.instrumentCode = instrumentCode;
        this.qty = qty;
    }

    public org.apache.avro.specific.SpecificData getSpecificData() {
        return MODEL$;
    }

    public org.apache.avro.Schema getSchema() {
        return SCHEMA$;
    }

    // Used by DatumWriter.  Applications should not call.
    public java.lang.Object get(int field$) {
        return switch (field$) {
            case 0 -> epochMillis;
            case 1 -> brokerId;
            case 2 -> investorId;
            case 3 -> exchangeCode;
            case 4 -> instrumentCode;
            case 5 -> qty;
            default -> throw new IndexOutOfBoundsException("Invalid index: " + field$);
        };
    }

    // Used by DatumReader.  Applications should not call.
    public void put(int field$, java.lang.Object value$) {
        switch (field$) {
            case 0:
                epochMillis = (java.lang.Long) value$;
                break;
            case 1:
                brokerId = value$ != null ? value$.toString() : null;
                break;
            case 2:
                investorId = value$ != null ? value$.toString() : null;
                break;
            case 3:
                exchangeCode = value$ != null ? value$.toString() : null;
                break;
            case 4:
                instrumentCode = value$ != null ? value$.toString() : null;
                break;
            case 5:
                qty = (java.lang.Integer) value$;
                break;
            default:
                throw new IndexOutOfBoundsException("Invalid index: " + field$);
        }
    }

    /**
     * Gets the value of the 'epochMillis' field.
     *
     * @return The value of the 'epochMillis' field.
     */
    public long getEpochMillis() {
        return epochMillis;
    }


    /**
     * Sets the value of the 'epochMillis' field.
     *
     * @param value the value to set.
     */
    public PositionsEvent setEpochMillis(long value) {
        this.epochMillis = value;
        return this;
    }

    /**
     * Gets the value of the 'brokerId' field.
     *
     * @return The value of the 'brokerId' field.
     */
    public java.lang.String getBrokerId() {
        return brokerId;
    }


    /**
     * Sets the value of the 'brokerId' field.
     *
     * @param value the value to set.
     */
    public PositionsEvent setBrokerId(java.lang.String value) {
        this.brokerId = value;
        return this;
    }

    /**
     * Gets the value of the 'investorId' field.
     *
     * @return The value of the 'investorId' field.
     */
    public java.lang.String getInvestorId() {
        return investorId;
    }


    /**
     * Sets the value of the 'investorId' field.
     *
     * @param value the value to set.
     */
    public PositionsEvent setInvestorId(java.lang.String value) {
        this.investorId = value;
        return this;
    }

    /**
     * Gets the value of the 'exchangeCode' field.
     *
     * @return The value of the 'exchangeCode' field.
     */
    public java.lang.String getExchangeCode() {
        return exchangeCode;
    }


    /**
     * Sets the value of the 'exchangeCode' field.
     *
     * @param value the value to set.
     */
    public PositionsEvent setExchangeCode(java.lang.String value) {
        this.exchangeCode = value;
        return this;
    }

    /**
     * Gets the value of the 'instrumentCode' field.
     *
     * @return The value of the 'instrumentCode' field.
     */
    public java.lang.String getInstrumentCode() {
        return instrumentCode;
    }


    /**
     * Sets the value of the 'instrumentCode' field.
     *
     * @param value the value to set.
     */
    public PositionsEvent setInstrumentCode(java.lang.String value) {
        this.instrumentCode = value;
        return this;
    }

    /**
     * Gets the value of the 'qty' field.
     *
     * @return The value of the 'qty' field.
     */
    public int getQty() {
        return qty;
    }


    /**
     * Sets the value of the 'qty' field.
     *
     * @param value the value to set.
     */
    public PositionsEvent setQty(int value) {
        this.qty = value;
        return this;
    }

    /**
     * Creates a new PositionsEvent RecordBuilder.
     *
     * @return A new PositionsEvent RecordBuilder
     */
    public static io.rapid.core.protocol.avro.event.PositionsEvent.Builder newBuilder() {
        return new io.rapid.core.protocol.avro.event.PositionsEvent.Builder();
    }

    /**
     * Creates a new PositionsEvent RecordBuilder by copying an existing Builder.
     *
     * @param other The existing builder to copy.
     * @return A new PositionsEvent RecordBuilder
     */
    public static io.rapid.core.protocol.avro.event.PositionsEvent.Builder newBuilder(io.rapid.core.protocol.avro.event.PositionsEvent.Builder other) {
        if (other == null) {
            return new io.rapid.core.protocol.avro.event.PositionsEvent.Builder();
        } else {
            return new io.rapid.core.protocol.avro.event.PositionsEvent.Builder(other);
        }
    }

    /**
     * Creates a new PositionsEvent RecordBuilder by copying an existing PositionsEvent instance.
     *
     * @param other The existing instance to copy.
     * @return A new PositionsEvent RecordBuilder
     */
    public static io.rapid.core.protocol.avro.event.PositionsEvent.Builder newBuilder(io.rapid.core.protocol.avro.event.PositionsEvent other) {
        if (other == null) {
            return new io.rapid.core.protocol.avro.event.PositionsEvent.Builder();
        } else {
            return new io.rapid.core.protocol.avro.event.PositionsEvent.Builder(other);
        }
    }

    /**
     * RecordBuilder for PositionsEvent instances.
     */
    @org.apache.avro.specific.AvroGenerated
    public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PositionsEvent>
            implements org.apache.avro.data.RecordBuilder<PositionsEvent> {

        private long epochMillis;
        private java.lang.String brokerId;
        private java.lang.String investorId;
        private java.lang.String exchangeCode;
        private java.lang.String instrumentCode;
        private int qty;

        /**
         * Creates a new Builder
         */
        private Builder() {
            super(SCHEMA$, MODEL$);
        }

        /**
         * Creates a Builder by copying an existing Builder.
         *
         * @param other The existing Builder to copy.
         */
        private Builder(io.rapid.core.protocol.avro.event.PositionsEvent.Builder other) {
            super(other);
            if (isValidValue(fields()[0], other.epochMillis)) {
                this.epochMillis = data().deepCopy(fields()[0].schema(), other.epochMillis);
                fieldSetFlags()[0] = other.fieldSetFlags()[0];
            }
            if (isValidValue(fields()[1], other.brokerId)) {
                this.brokerId = data().deepCopy(fields()[1].schema(), other.brokerId);
                fieldSetFlags()[1] = other.fieldSetFlags()[1];
            }
            if (isValidValue(fields()[2], other.investorId)) {
                this.investorId = data().deepCopy(fields()[2].schema(), other.investorId);
                fieldSetFlags()[2] = other.fieldSetFlags()[2];
            }
            if (isValidValue(fields()[3], other.exchangeCode)) {
                this.exchangeCode = data().deepCopy(fields()[3].schema(), other.exchangeCode);
                fieldSetFlags()[3] = other.fieldSetFlags()[3];
            }
            if (isValidValue(fields()[4], other.instrumentCode)) {
                this.instrumentCode = data().deepCopy(fields()[4].schema(), other.instrumentCode);
                fieldSetFlags()[4] = other.fieldSetFlags()[4];
            }
            if (isValidValue(fields()[5], other.qty)) {
                this.qty = data().deepCopy(fields()[5].schema(), other.qty);
                fieldSetFlags()[5] = other.fieldSetFlags()[5];
            }
        }

        /**
         * Creates a Builder by copying an existing PositionsEvent instance
         *
         * @param other The existing instance to copy.
         */
        private Builder(io.rapid.core.protocol.avro.event.PositionsEvent other) {
            super(SCHEMA$, MODEL$);
            if (isValidValue(fields()[0], other.epochMillis)) {
                this.epochMillis = data().deepCopy(fields()[0].schema(), other.epochMillis);
                fieldSetFlags()[0] = true;
            }
            if (isValidValue(fields()[1], other.brokerId)) {
                this.brokerId = data().deepCopy(fields()[1].schema(), other.brokerId);
                fieldSetFlags()[1] = true;
            }
            if (isValidValue(fields()[2], other.investorId)) {
                this.investorId = data().deepCopy(fields()[2].schema(), other.investorId);
                fieldSetFlags()[2] = true;
            }
            if (isValidValue(fields()[3], other.exchangeCode)) {
                this.exchangeCode = data().deepCopy(fields()[3].schema(), other.exchangeCode);
                fieldSetFlags()[3] = true;
            }
            if (isValidValue(fields()[4], other.instrumentCode)) {
                this.instrumentCode = data().deepCopy(fields()[4].schema(), other.instrumentCode);
                fieldSetFlags()[4] = true;
            }
            if (isValidValue(fields()[5], other.qty)) {
                this.qty = data().deepCopy(fields()[5].schema(), other.qty);
                fieldSetFlags()[5] = true;
            }
        }

        /**
         * Gets the value of the 'epochMillis' field.
         *
         * @return The value.
         */
        public long getEpochMillis() {
            return epochMillis;
        }


        /**
         * Sets the value of the 'epochMillis' field.
         *
         * @param value The value of 'epochMillis'.
         * @return This builder.
         */
        public io.rapid.core.protocol.avro.event.PositionsEvent.Builder setEpochMillis(long value) {
            validate(fields()[0], value);
            this.epochMillis = value;
            fieldSetFlags()[0] = true;
            return this;
        }

        /**
         * Checks whether the 'epochMillis' field has been set.
         *
         * @return True if the 'epochMillis' field has been set, false otherwise.
         */
        public boolean hasEpochMillis() {
            return fieldSetFlags()[0];
        }


        /**
         * Clears the value of the 'epochMillis' field.
         *
         * @return This builder.
         */
        public io.rapid.core.protocol.avro.event.PositionsEvent.Builder clearEpochMillis() {
            fieldSetFlags()[0] = false;
            return this;
        }

        /**
         * Gets the value of the 'brokerId' field.
         *
         * @return The value.
         */
        public java.lang.String getBrokerId() {
            return brokerId;
        }


        /**
         * Sets the value of the 'brokerId' field.
         *
         * @param value The value of 'brokerId'.
         * @return This builder.
         */
        public io.rapid.core.protocol.avro.event.PositionsEvent.Builder setBrokerId(java.lang.String value) {
            validate(fields()[1], value);
            this.brokerId = value;
            fieldSetFlags()[1] = true;
            return this;
        }

        /**
         * Checks whether the 'brokerId' field has been set.
         *
         * @return True if the 'brokerId' field has been set, false otherwise.
         */
        public boolean hasBrokerId() {
            return fieldSetFlags()[1];
        }


        /**
         * Clears the value of the 'brokerId' field.
         *
         * @return This builder.
         */
        public io.rapid.core.protocol.avro.event.PositionsEvent.Builder clearBrokerId() {
            brokerId = null;
            fieldSetFlags()[1] = false;
            return this;
        }

        /**
         * Gets the value of the 'investorId' field.
         *
         * @return The value.
         */
        public java.lang.String getInvestorId() {
            return investorId;
        }


        /**
         * Sets the value of the 'investorId' field.
         *
         * @param value The value of 'investorId'.
         * @return This builder.
         */
        public io.rapid.core.protocol.avro.event.PositionsEvent.Builder setInvestorId(java.lang.String value) {
            validate(fields()[2], value);
            this.investorId = value;
            fieldSetFlags()[2] = true;
            return this;
        }

        /**
         * Checks whether the 'investorId' field has been set.
         *
         * @return True if the 'investorId' field has been set, false otherwise.
         */
        public boolean hasInvestorId() {
            return fieldSetFlags()[2];
        }


        /**
         * Clears the value of the 'investorId' field.
         *
         * @return This builder.
         */
        public io.rapid.core.protocol.avro.event.PositionsEvent.Builder clearInvestorId() {
            investorId = null;
            fieldSetFlags()[2] = false;
            return this;
        }

        /**
         * Gets the value of the 'exchangeCode' field.
         *
         * @return The value.
         */
        public java.lang.String getExchangeCode() {
            return exchangeCode;
        }


        /**
         * Sets the value of the 'exchangeCode' field.
         *
         * @param value The value of 'exchangeCode'.
         * @return This builder.
         */
        public io.rapid.core.protocol.avro.event.PositionsEvent.Builder setExchangeCode(java.lang.String value) {
            validate(fields()[3], value);
            this.exchangeCode = value;
            fieldSetFlags()[3] = true;
            return this;
        }

        /**
         * Checks whether the 'exchangeCode' field has been set.
         *
         * @return True if the 'exchangeCode' field has been set, false otherwise.
         */
        public boolean hasExchangeCode() {
            return fieldSetFlags()[3];
        }


        /**
         * Clears the value of the 'exchangeCode' field.
         *
         * @return This builder.
         */
        public io.rapid.core.protocol.avro.event.PositionsEvent.Builder clearExchangeCode() {
            exchangeCode = null;
            fieldSetFlags()[3] = false;
            return this;
        }

        /**
         * Gets the value of the 'instrumentCode' field.
         *
         * @return The value.
         */
        public java.lang.String getInstrumentCode() {
            return instrumentCode;
        }


        /**
         * Sets the value of the 'instrumentCode' field.
         *
         * @param value The value of 'instrumentCode'.
         * @return This builder.
         */
        public io.rapid.core.protocol.avro.event.PositionsEvent.Builder setInstrumentCode(java.lang.String value) {
            validate(fields()[4], value);
            this.instrumentCode = value;
            fieldSetFlags()[4] = true;
            return this;
        }

        /**
         * Checks whether the 'instrumentCode' field has been set.
         *
         * @return True if the 'instrumentCode' field has been set, false otherwise.
         */
        public boolean hasInstrumentCode() {
            return fieldSetFlags()[4];
        }


        /**
         * Clears the value of the 'instrumentCode' field.
         *
         * @return This builder.
         */
        public io.rapid.core.protocol.avro.event.PositionsEvent.Builder clearInstrumentCode() {
            instrumentCode = null;
            fieldSetFlags()[4] = false;
            return this;
        }

        /**
         * Gets the value of the 'qty' field.
         *
         * @return The value.
         */
        public int getQty() {
            return qty;
        }


        /**
         * Sets the value of the 'qty' field.
         *
         * @param value The value of 'qty'.
         * @return This builder.
         */
        public io.rapid.core.protocol.avro.event.PositionsEvent.Builder setQty(int value) {
            validate(fields()[5], value);
            this.qty = value;
            fieldSetFlags()[5] = true;
            return this;
        }

        /**
         * Checks whether the 'qty' field has been set.
         *
         * @return True if the 'qty' field has been set, false otherwise.
         */
        public boolean hasQty() {
            return fieldSetFlags()[5];
        }


        /**
         * Clears the value of the 'qty' field.
         *
         * @return This builder.
         */
        public io.rapid.core.protocol.avro.event.PositionsEvent.Builder clearQty() {
            fieldSetFlags()[5] = false;
            return this;
        }

        @Override
        public PositionsEvent build() {
            try {
                PositionsEvent record = new PositionsEvent();
                record.epochMillis = fieldSetFlags()[0] ? this.epochMillis : (java.lang.Long) defaultValue(fields()[0]);
                record.brokerId = fieldSetFlags()[1] ? this.brokerId : (java.lang.String) defaultValue(fields()[1]);
                record.investorId = fieldSetFlags()[2] ? this.investorId : (java.lang.String) defaultValue(fields()[2]);
                record.exchangeCode = fieldSetFlags()[3] ? this.exchangeCode : (java.lang.String) defaultValue(fields()[3]);
                record.instrumentCode = fieldSetFlags()[4] ? this.instrumentCode : (java.lang.String) defaultValue(fields()[4]);
                record.qty = fieldSetFlags()[5] ? this.qty : (java.lang.Integer) defaultValue(fields()[5]);
                return record;
            } catch (org.apache.avro.AvroMissingFieldException e) {
                throw e;
            } catch (java.lang.Exception e) {
                throw new org.apache.avro.AvroRuntimeException(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static final org.apache.avro.io.DatumWriter<PositionsEvent>
            WRITER$ = (org.apache.avro.io.DatumWriter<PositionsEvent>) MODEL$.createDatumWriter(SCHEMA$);

    @Override
    public void writeExternal(java.io.ObjectOutput out)
            throws java.io.IOException {
        WRITER$.write(this, SpecificData.getEncoder(out));
    }

    @SuppressWarnings("unchecked")
    private static final org.apache.avro.io.DatumReader<PositionsEvent>
            READER$ = (org.apache.avro.io.DatumReader<PositionsEvent>) MODEL$.createDatumReader(SCHEMA$);

    @Override
    public void readExternal(java.io.ObjectInput in)
            throws java.io.IOException {
        READER$.read(this, SpecificData.getDecoder(in));
    }

    @Override
    protected boolean hasCustomCoders() {
        return true;
    }

    @Override
    public void customEncode(org.apache.avro.io.Encoder out)
            throws java.io.IOException {
        out.writeLong(this.epochMillis);

        out.writeString(this.brokerId);

        out.writeString(this.investorId);

        out.writeString(this.exchangeCode);

        out.writeString(this.instrumentCode);

        out.writeInt(this.qty);

    }

    @Override
    public void customDecode(org.apache.avro.io.ResolvingDecoder in)
            throws java.io.IOException {
        org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
        if (fieldOrder == null) {
            this.epochMillis = in.readLong();

            this.brokerId = in.readString();

            this.investorId = in.readString();

            this.exchangeCode = in.readString();

            this.instrumentCode = in.readString();

            this.qty = in.readInt();

        } else {
            for (int i = 0; i < 6; i++) {
                switch (fieldOrder[i].pos()) {
                    case 0:
                        this.epochMillis = in.readLong();
                        break;

                    case 1:
                        this.brokerId = in.readString();
                        break;

                    case 2:
                        this.investorId = in.readString();
                        break;

                    case 3:
                        this.exchangeCode = in.readString();
                        break;

                    case 4:
                        this.instrumentCode = in.readString();
                        break;

                    case 5:
                        this.qty = in.readInt();
                        break;

                    default:
                        throw new java.io.IOException("Corrupt ResolvingDecoder.");
                }
            }
        }
    }
}









