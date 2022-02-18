// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: provenance/marker/v1/marker.proto

package io.provenance.marker.v1;

/**
 * <pre>
 * EventDenomUnit denom units for set denom metadata event
 * </pre>
 *
 * Protobuf type {@code provenance.marker.v1.EventDenomUnit}
 */
public final class EventDenomUnit extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:provenance.marker.v1.EventDenomUnit)
    EventDenomUnitOrBuilder {
private static final long serialVersionUID = 0L;
  // Use EventDenomUnit.newBuilder() to construct.
  private EventDenomUnit(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private EventDenomUnit() {
    denom_ = "";
    exponent_ = "";
    aliases_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new EventDenomUnit();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private EventDenomUnit(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            denom_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            exponent_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              aliases_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000001;
            }
            aliases_.add(s);
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        aliases_ = aliases_.getUnmodifiableView();
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.provenance.marker.v1.Marker.internal_static_provenance_marker_v1_EventDenomUnit_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.provenance.marker.v1.Marker.internal_static_provenance_marker_v1_EventDenomUnit_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.provenance.marker.v1.EventDenomUnit.class, io.provenance.marker.v1.EventDenomUnit.Builder.class);
  }

  public static final int DENOM_FIELD_NUMBER = 1;
  private volatile java.lang.Object denom_;
  /**
   * <code>string denom = 1;</code>
   * @return The denom.
   */
  @java.lang.Override
  public java.lang.String getDenom() {
    java.lang.Object ref = denom_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      denom_ = s;
      return s;
    }
  }
  /**
   * <code>string denom = 1;</code>
   * @return The bytes for denom.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getDenomBytes() {
    java.lang.Object ref = denom_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      denom_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int EXPONENT_FIELD_NUMBER = 2;
  private volatile java.lang.Object exponent_;
  /**
   * <code>string exponent = 2;</code>
   * @return The exponent.
   */
  @java.lang.Override
  public java.lang.String getExponent() {
    java.lang.Object ref = exponent_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      exponent_ = s;
      return s;
    }
  }
  /**
   * <code>string exponent = 2;</code>
   * @return The bytes for exponent.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getExponentBytes() {
    java.lang.Object ref = exponent_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      exponent_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ALIASES_FIELD_NUMBER = 3;
  private com.google.protobuf.LazyStringList aliases_;
  /**
   * <code>repeated string aliases = 3;</code>
   * @return A list containing the aliases.
   */
  public com.google.protobuf.ProtocolStringList
      getAliasesList() {
    return aliases_;
  }
  /**
   * <code>repeated string aliases = 3;</code>
   * @return The count of aliases.
   */
  public int getAliasesCount() {
    return aliases_.size();
  }
  /**
   * <code>repeated string aliases = 3;</code>
   * @param index The index of the element to return.
   * @return The aliases at the given index.
   */
  public java.lang.String getAliases(int index) {
    return aliases_.get(index);
  }
  /**
   * <code>repeated string aliases = 3;</code>
   * @param index The index of the value to return.
   * @return The bytes of the aliases at the given index.
   */
  public com.google.protobuf.ByteString
      getAliasesBytes(int index) {
    return aliases_.getByteString(index);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getDenomBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, denom_);
    }
    if (!getExponentBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, exponent_);
    }
    for (int i = 0; i < aliases_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, aliases_.getRaw(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getDenomBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, denom_);
    }
    if (!getExponentBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, exponent_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < aliases_.size(); i++) {
        dataSize += computeStringSizeNoTag(aliases_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getAliasesList().size();
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof io.provenance.marker.v1.EventDenomUnit)) {
      return super.equals(obj);
    }
    io.provenance.marker.v1.EventDenomUnit other = (io.provenance.marker.v1.EventDenomUnit) obj;

    if (!getDenom()
        .equals(other.getDenom())) return false;
    if (!getExponent()
        .equals(other.getExponent())) return false;
    if (!getAliasesList()
        .equals(other.getAliasesList())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + DENOM_FIELD_NUMBER;
    hash = (53 * hash) + getDenom().hashCode();
    hash = (37 * hash) + EXPONENT_FIELD_NUMBER;
    hash = (53 * hash) + getExponent().hashCode();
    if (getAliasesCount() > 0) {
      hash = (37 * hash) + ALIASES_FIELD_NUMBER;
      hash = (53 * hash) + getAliasesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.provenance.marker.v1.EventDenomUnit parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.provenance.marker.v1.EventDenomUnit parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.provenance.marker.v1.EventDenomUnit parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.provenance.marker.v1.EventDenomUnit parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.provenance.marker.v1.EventDenomUnit parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.provenance.marker.v1.EventDenomUnit parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.provenance.marker.v1.EventDenomUnit parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.provenance.marker.v1.EventDenomUnit parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.provenance.marker.v1.EventDenomUnit parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.provenance.marker.v1.EventDenomUnit parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.provenance.marker.v1.EventDenomUnit parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.provenance.marker.v1.EventDenomUnit parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(io.provenance.marker.v1.EventDenomUnit prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * EventDenomUnit denom units for set denom metadata event
   * </pre>
   *
   * Protobuf type {@code provenance.marker.v1.EventDenomUnit}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:provenance.marker.v1.EventDenomUnit)
      io.provenance.marker.v1.EventDenomUnitOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.provenance.marker.v1.Marker.internal_static_provenance_marker_v1_EventDenomUnit_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.provenance.marker.v1.Marker.internal_static_provenance_marker_v1_EventDenomUnit_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.provenance.marker.v1.EventDenomUnit.class, io.provenance.marker.v1.EventDenomUnit.Builder.class);
    }

    // Construct using io.provenance.marker.v1.EventDenomUnit.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      denom_ = "";

      exponent_ = "";

      aliases_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.provenance.marker.v1.Marker.internal_static_provenance_marker_v1_EventDenomUnit_descriptor;
    }

    @java.lang.Override
    public io.provenance.marker.v1.EventDenomUnit getDefaultInstanceForType() {
      return io.provenance.marker.v1.EventDenomUnit.getDefaultInstance();
    }

    @java.lang.Override
    public io.provenance.marker.v1.EventDenomUnit build() {
      io.provenance.marker.v1.EventDenomUnit result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.provenance.marker.v1.EventDenomUnit buildPartial() {
      io.provenance.marker.v1.EventDenomUnit result = new io.provenance.marker.v1.EventDenomUnit(this);
      int from_bitField0_ = bitField0_;
      result.denom_ = denom_;
      result.exponent_ = exponent_;
      if (((bitField0_ & 0x00000001) != 0)) {
        aliases_ = aliases_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.aliases_ = aliases_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof io.provenance.marker.v1.EventDenomUnit) {
        return mergeFrom((io.provenance.marker.v1.EventDenomUnit)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.provenance.marker.v1.EventDenomUnit other) {
      if (other == io.provenance.marker.v1.EventDenomUnit.getDefaultInstance()) return this;
      if (!other.getDenom().isEmpty()) {
        denom_ = other.denom_;
        onChanged();
      }
      if (!other.getExponent().isEmpty()) {
        exponent_ = other.exponent_;
        onChanged();
      }
      if (!other.aliases_.isEmpty()) {
        if (aliases_.isEmpty()) {
          aliases_ = other.aliases_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureAliasesIsMutable();
          aliases_.addAll(other.aliases_);
        }
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      io.provenance.marker.v1.EventDenomUnit parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.provenance.marker.v1.EventDenomUnit) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object denom_ = "";
    /**
     * <code>string denom = 1;</code>
     * @return The denom.
     */
    public java.lang.String getDenom() {
      java.lang.Object ref = denom_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        denom_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string denom = 1;</code>
     * @return The bytes for denom.
     */
    public com.google.protobuf.ByteString
        getDenomBytes() {
      java.lang.Object ref = denom_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        denom_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string denom = 1;</code>
     * @param value The denom to set.
     * @return This builder for chaining.
     */
    public Builder setDenom(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      denom_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string denom = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearDenom() {
      
      denom_ = getDefaultInstance().getDenom();
      onChanged();
      return this;
    }
    /**
     * <code>string denom = 1;</code>
     * @param value The bytes for denom to set.
     * @return This builder for chaining.
     */
    public Builder setDenomBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      denom_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object exponent_ = "";
    /**
     * <code>string exponent = 2;</code>
     * @return The exponent.
     */
    public java.lang.String getExponent() {
      java.lang.Object ref = exponent_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        exponent_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string exponent = 2;</code>
     * @return The bytes for exponent.
     */
    public com.google.protobuf.ByteString
        getExponentBytes() {
      java.lang.Object ref = exponent_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        exponent_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string exponent = 2;</code>
     * @param value The exponent to set.
     * @return This builder for chaining.
     */
    public Builder setExponent(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      exponent_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string exponent = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearExponent() {
      
      exponent_ = getDefaultInstance().getExponent();
      onChanged();
      return this;
    }
    /**
     * <code>string exponent = 2;</code>
     * @param value The bytes for exponent to set.
     * @return This builder for chaining.
     */
    public Builder setExponentBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      exponent_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.LazyStringList aliases_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureAliasesIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        aliases_ = new com.google.protobuf.LazyStringArrayList(aliases_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated string aliases = 3;</code>
     * @return A list containing the aliases.
     */
    public com.google.protobuf.ProtocolStringList
        getAliasesList() {
      return aliases_.getUnmodifiableView();
    }
    /**
     * <code>repeated string aliases = 3;</code>
     * @return The count of aliases.
     */
    public int getAliasesCount() {
      return aliases_.size();
    }
    /**
     * <code>repeated string aliases = 3;</code>
     * @param index The index of the element to return.
     * @return The aliases at the given index.
     */
    public java.lang.String getAliases(int index) {
      return aliases_.get(index);
    }
    /**
     * <code>repeated string aliases = 3;</code>
     * @param index The index of the value to return.
     * @return The bytes of the aliases at the given index.
     */
    public com.google.protobuf.ByteString
        getAliasesBytes(int index) {
      return aliases_.getByteString(index);
    }
    /**
     * <code>repeated string aliases = 3;</code>
     * @param index The index to set the value at.
     * @param value The aliases to set.
     * @return This builder for chaining.
     */
    public Builder setAliases(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureAliasesIsMutable();
      aliases_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string aliases = 3;</code>
     * @param value The aliases to add.
     * @return This builder for chaining.
     */
    public Builder addAliases(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureAliasesIsMutable();
      aliases_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string aliases = 3;</code>
     * @param values The aliases to add.
     * @return This builder for chaining.
     */
    public Builder addAllAliases(
        java.lang.Iterable<java.lang.String> values) {
      ensureAliasesIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, aliases_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string aliases = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearAliases() {
      aliases_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string aliases = 3;</code>
     * @param value The bytes of the aliases to add.
     * @return This builder for chaining.
     */
    public Builder addAliasesBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureAliasesIsMutable();
      aliases_.add(value);
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:provenance.marker.v1.EventDenomUnit)
  }

  // @@protoc_insertion_point(class_scope:provenance.marker.v1.EventDenomUnit)
  private static final io.provenance.marker.v1.EventDenomUnit DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.provenance.marker.v1.EventDenomUnit();
  }

  public static io.provenance.marker.v1.EventDenomUnit getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<EventDenomUnit>
      PARSER = new com.google.protobuf.AbstractParser<EventDenomUnit>() {
    @java.lang.Override
    public EventDenomUnit parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new EventDenomUnit(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<EventDenomUnit> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<EventDenomUnit> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.provenance.marker.v1.EventDenomUnit getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

