// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: provenance/marker/v1/query.proto

package io.provenance.marker.v1;

/**
 * <pre>
 * QueryParamsResponse is the response type for the Query/Params RPC method.
 * </pre>
 *
 * Protobuf type {@code provenance.marker.v1.QueryParamsResponse}
 */
public final class QueryParamsResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:provenance.marker.v1.QueryParamsResponse)
    QueryParamsResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use QueryParamsResponse.newBuilder() to construct.
  private QueryParamsResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueryParamsResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new QueryParamsResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private QueryParamsResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
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
            io.provenance.marker.v1.Params.Builder subBuilder = null;
            if (params_ != null) {
              subBuilder = params_.toBuilder();
            }
            params_ = input.readMessage(io.provenance.marker.v1.Params.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(params_);
              params_ = subBuilder.buildPartial();
            }

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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.provenance.marker.v1.QueryOuterClass.internal_static_provenance_marker_v1_QueryParamsResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.provenance.marker.v1.QueryOuterClass.internal_static_provenance_marker_v1_QueryParamsResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.provenance.marker.v1.QueryParamsResponse.class, io.provenance.marker.v1.QueryParamsResponse.Builder.class);
  }

  public static final int PARAMS_FIELD_NUMBER = 1;
  private io.provenance.marker.v1.Params params_;
  /**
   * <pre>
   * params defines the parameters of the module.
   * </pre>
   *
   * <code>.provenance.marker.v1.Params params = 1 [(.gogoproto.nullable) = false];</code>
   * @return Whether the params field is set.
   */
  @java.lang.Override
  public boolean hasParams() {
    return params_ != null;
  }
  /**
   * <pre>
   * params defines the parameters of the module.
   * </pre>
   *
   * <code>.provenance.marker.v1.Params params = 1 [(.gogoproto.nullable) = false];</code>
   * @return The params.
   */
  @java.lang.Override
  public io.provenance.marker.v1.Params getParams() {
    return params_ == null ? io.provenance.marker.v1.Params.getDefaultInstance() : params_;
  }
  /**
   * <pre>
   * params defines the parameters of the module.
   * </pre>
   *
   * <code>.provenance.marker.v1.Params params = 1 [(.gogoproto.nullable) = false];</code>
   */
  @java.lang.Override
  public io.provenance.marker.v1.ParamsOrBuilder getParamsOrBuilder() {
    return getParams();
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
    if (params_ != null) {
      output.writeMessage(1, getParams());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (params_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getParams());
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
    if (!(obj instanceof io.provenance.marker.v1.QueryParamsResponse)) {
      return super.equals(obj);
    }
    io.provenance.marker.v1.QueryParamsResponse other = (io.provenance.marker.v1.QueryParamsResponse) obj;

    if (hasParams() != other.hasParams()) return false;
    if (hasParams()) {
      if (!getParams()
          .equals(other.getParams())) return false;
    }
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
    if (hasParams()) {
      hash = (37 * hash) + PARAMS_FIELD_NUMBER;
      hash = (53 * hash) + getParams().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.provenance.marker.v1.QueryParamsResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.provenance.marker.v1.QueryParamsResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.provenance.marker.v1.QueryParamsResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.provenance.marker.v1.QueryParamsResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.provenance.marker.v1.QueryParamsResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.provenance.marker.v1.QueryParamsResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.provenance.marker.v1.QueryParamsResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.provenance.marker.v1.QueryParamsResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.provenance.marker.v1.QueryParamsResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.provenance.marker.v1.QueryParamsResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.provenance.marker.v1.QueryParamsResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.provenance.marker.v1.QueryParamsResponse parseFrom(
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
  public static Builder newBuilder(io.provenance.marker.v1.QueryParamsResponse prototype) {
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
   * QueryParamsResponse is the response type for the Query/Params RPC method.
   * </pre>
   *
   * Protobuf type {@code provenance.marker.v1.QueryParamsResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:provenance.marker.v1.QueryParamsResponse)
      io.provenance.marker.v1.QueryParamsResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.provenance.marker.v1.QueryOuterClass.internal_static_provenance_marker_v1_QueryParamsResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.provenance.marker.v1.QueryOuterClass.internal_static_provenance_marker_v1_QueryParamsResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.provenance.marker.v1.QueryParamsResponse.class, io.provenance.marker.v1.QueryParamsResponse.Builder.class);
    }

    // Construct using io.provenance.marker.v1.QueryParamsResponse.newBuilder()
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
      if (paramsBuilder_ == null) {
        params_ = null;
      } else {
        params_ = null;
        paramsBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.provenance.marker.v1.QueryOuterClass.internal_static_provenance_marker_v1_QueryParamsResponse_descriptor;
    }

    @java.lang.Override
    public io.provenance.marker.v1.QueryParamsResponse getDefaultInstanceForType() {
      return io.provenance.marker.v1.QueryParamsResponse.getDefaultInstance();
    }

    @java.lang.Override
    public io.provenance.marker.v1.QueryParamsResponse build() {
      io.provenance.marker.v1.QueryParamsResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.provenance.marker.v1.QueryParamsResponse buildPartial() {
      io.provenance.marker.v1.QueryParamsResponse result = new io.provenance.marker.v1.QueryParamsResponse(this);
      if (paramsBuilder_ == null) {
        result.params_ = params_;
      } else {
        result.params_ = paramsBuilder_.build();
      }
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
      if (other instanceof io.provenance.marker.v1.QueryParamsResponse) {
        return mergeFrom((io.provenance.marker.v1.QueryParamsResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.provenance.marker.v1.QueryParamsResponse other) {
      if (other == io.provenance.marker.v1.QueryParamsResponse.getDefaultInstance()) return this;
      if (other.hasParams()) {
        mergeParams(other.getParams());
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
      io.provenance.marker.v1.QueryParamsResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.provenance.marker.v1.QueryParamsResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private io.provenance.marker.v1.Params params_;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.provenance.marker.v1.Params, io.provenance.marker.v1.Params.Builder, io.provenance.marker.v1.ParamsOrBuilder> paramsBuilder_;
    /**
     * <pre>
     * params defines the parameters of the module.
     * </pre>
     *
     * <code>.provenance.marker.v1.Params params = 1 [(.gogoproto.nullable) = false];</code>
     * @return Whether the params field is set.
     */
    public boolean hasParams() {
      return paramsBuilder_ != null || params_ != null;
    }
    /**
     * <pre>
     * params defines the parameters of the module.
     * </pre>
     *
     * <code>.provenance.marker.v1.Params params = 1 [(.gogoproto.nullable) = false];</code>
     * @return The params.
     */
    public io.provenance.marker.v1.Params getParams() {
      if (paramsBuilder_ == null) {
        return params_ == null ? io.provenance.marker.v1.Params.getDefaultInstance() : params_;
      } else {
        return paramsBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * params defines the parameters of the module.
     * </pre>
     *
     * <code>.provenance.marker.v1.Params params = 1 [(.gogoproto.nullable) = false];</code>
     */
    public Builder setParams(io.provenance.marker.v1.Params value) {
      if (paramsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        params_ = value;
        onChanged();
      } else {
        paramsBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     * params defines the parameters of the module.
     * </pre>
     *
     * <code>.provenance.marker.v1.Params params = 1 [(.gogoproto.nullable) = false];</code>
     */
    public Builder setParams(
        io.provenance.marker.v1.Params.Builder builderForValue) {
      if (paramsBuilder_ == null) {
        params_ = builderForValue.build();
        onChanged();
      } else {
        paramsBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     * params defines the parameters of the module.
     * </pre>
     *
     * <code>.provenance.marker.v1.Params params = 1 [(.gogoproto.nullable) = false];</code>
     */
    public Builder mergeParams(io.provenance.marker.v1.Params value) {
      if (paramsBuilder_ == null) {
        if (params_ != null) {
          params_ =
            io.provenance.marker.v1.Params.newBuilder(params_).mergeFrom(value).buildPartial();
        } else {
          params_ = value;
        }
        onChanged();
      } else {
        paramsBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     * params defines the parameters of the module.
     * </pre>
     *
     * <code>.provenance.marker.v1.Params params = 1 [(.gogoproto.nullable) = false];</code>
     */
    public Builder clearParams() {
      if (paramsBuilder_ == null) {
        params_ = null;
        onChanged();
      } else {
        params_ = null;
        paramsBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     * params defines the parameters of the module.
     * </pre>
     *
     * <code>.provenance.marker.v1.Params params = 1 [(.gogoproto.nullable) = false];</code>
     */
    public io.provenance.marker.v1.Params.Builder getParamsBuilder() {
      
      onChanged();
      return getParamsFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * params defines the parameters of the module.
     * </pre>
     *
     * <code>.provenance.marker.v1.Params params = 1 [(.gogoproto.nullable) = false];</code>
     */
    public io.provenance.marker.v1.ParamsOrBuilder getParamsOrBuilder() {
      if (paramsBuilder_ != null) {
        return paramsBuilder_.getMessageOrBuilder();
      } else {
        return params_ == null ?
            io.provenance.marker.v1.Params.getDefaultInstance() : params_;
      }
    }
    /**
     * <pre>
     * params defines the parameters of the module.
     * </pre>
     *
     * <code>.provenance.marker.v1.Params params = 1 [(.gogoproto.nullable) = false];</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.provenance.marker.v1.Params, io.provenance.marker.v1.Params.Builder, io.provenance.marker.v1.ParamsOrBuilder> 
        getParamsFieldBuilder() {
      if (paramsBuilder_ == null) {
        paramsBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.provenance.marker.v1.Params, io.provenance.marker.v1.Params.Builder, io.provenance.marker.v1.ParamsOrBuilder>(
                getParams(),
                getParentForChildren(),
                isClean());
        params_ = null;
      }
      return paramsBuilder_;
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


    // @@protoc_insertion_point(builder_scope:provenance.marker.v1.QueryParamsResponse)
  }

  // @@protoc_insertion_point(class_scope:provenance.marker.v1.QueryParamsResponse)
  private static final io.provenance.marker.v1.QueryParamsResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.provenance.marker.v1.QueryParamsResponse();
  }

  public static io.provenance.marker.v1.QueryParamsResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QueryParamsResponse>
      PARSER = new com.google.protobuf.AbstractParser<QueryParamsResponse>() {
    @java.lang.Override
    public QueryParamsResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new QueryParamsResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueryParamsResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueryParamsResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.provenance.marker.v1.QueryParamsResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

