// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cosmos/authz/v1beta1/genesis.proto

package com.cosmos.authz.v1beta1;

public final class GenesisProto {
  private GenesisProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface GenesisStateOrBuilder extends
      // @@protoc_insertion_point(interface_extends:cosmos.authz.v1beta1.GenesisState)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     */
    java.util.List<com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization> 
        getAuthorizationList();
    /**
     * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     */
    com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization getAuthorization(int index);
    /**
     * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     */
    int getAuthorizationCount();
    /**
     * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     */
    java.util.List<? extends com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorizationOrBuilder> 
        getAuthorizationOrBuilderList();
    /**
     * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     */
    com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorizationOrBuilder getAuthorizationOrBuilder(
        int index);
  }
  /**
   * <pre>
   * GenesisState defines the authz module's genesis state.
   * </pre>
   *
   * Protobuf type {@code cosmos.authz.v1beta1.GenesisState}
   */
  public static final class GenesisState extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:cosmos.authz.v1beta1.GenesisState)
      GenesisStateOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use GenesisState.newBuilder() to construct.
    private GenesisState(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private GenesisState() {
      authorization_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new GenesisState();
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.cosmos.authz.v1beta1.GenesisProto.internal_static_cosmos_authz_v1beta1_GenesisState_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.cosmos.authz.v1beta1.GenesisProto.internal_static_cosmos_authz_v1beta1_GenesisState_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.cosmos.authz.v1beta1.GenesisProto.GenesisState.class, com.cosmos.authz.v1beta1.GenesisProto.GenesisState.Builder.class);
    }

    public static final int AUTHORIZATION_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization> authorization_;
    /**
     * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     */
    @java.lang.Override
    public java.util.List<com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization> getAuthorizationList() {
      return authorization_;
    }
    /**
     * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     */
    @java.lang.Override
    public java.util.List<? extends com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorizationOrBuilder> 
        getAuthorizationOrBuilderList() {
      return authorization_;
    }
    /**
     * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     */
    @java.lang.Override
    public int getAuthorizationCount() {
      return authorization_.size();
    }
    /**
     * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     */
    @java.lang.Override
    public com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization getAuthorization(int index) {
      return authorization_.get(index);
    }
    /**
     * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
     */
    @java.lang.Override
    public com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorizationOrBuilder getAuthorizationOrBuilder(
        int index) {
      return authorization_.get(index);
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
      for (int i = 0; i < authorization_.size(); i++) {
        output.writeMessage(1, authorization_.get(i));
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      for (int i = 0; i < authorization_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, authorization_.get(i));
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.cosmos.authz.v1beta1.GenesisProto.GenesisState)) {
        return super.equals(obj);
      }
      com.cosmos.authz.v1beta1.GenesisProto.GenesisState other = (com.cosmos.authz.v1beta1.GenesisProto.GenesisState) obj;

      if (!getAuthorizationList()
          .equals(other.getAuthorizationList())) return false;
      if (!getUnknownFields().equals(other.getUnknownFields())) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (getAuthorizationCount() > 0) {
        hash = (37 * hash) + AUTHORIZATION_FIELD_NUMBER;
        hash = (53 * hash) + getAuthorizationList().hashCode();
      }
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.cosmos.authz.v1beta1.GenesisProto.GenesisState parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.cosmos.authz.v1beta1.GenesisProto.GenesisState parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.cosmos.authz.v1beta1.GenesisProto.GenesisState parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.cosmos.authz.v1beta1.GenesisProto.GenesisState parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.cosmos.authz.v1beta1.GenesisProto.GenesisState parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.cosmos.authz.v1beta1.GenesisProto.GenesisState parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.cosmos.authz.v1beta1.GenesisProto.GenesisState parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.cosmos.authz.v1beta1.GenesisProto.GenesisState parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static com.cosmos.authz.v1beta1.GenesisProto.GenesisState parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static com.cosmos.authz.v1beta1.GenesisProto.GenesisState parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.cosmos.authz.v1beta1.GenesisProto.GenesisState parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.cosmos.authz.v1beta1.GenesisProto.GenesisState parseFrom(
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
    public static Builder newBuilder(com.cosmos.authz.v1beta1.GenesisProto.GenesisState prototype) {
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
     * GenesisState defines the authz module's genesis state.
     * </pre>
     *
     * Protobuf type {@code cosmos.authz.v1beta1.GenesisState}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:cosmos.authz.v1beta1.GenesisState)
        com.cosmos.authz.v1beta1.GenesisProto.GenesisStateOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.cosmos.authz.v1beta1.GenesisProto.internal_static_cosmos_authz_v1beta1_GenesisState_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.cosmos.authz.v1beta1.GenesisProto.internal_static_cosmos_authz_v1beta1_GenesisState_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.cosmos.authz.v1beta1.GenesisProto.GenesisState.class, com.cosmos.authz.v1beta1.GenesisProto.GenesisState.Builder.class);
      }

      // Construct using com.cosmos.authz.v1beta1.GenesisProto.GenesisState.newBuilder()
      private Builder() {

      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);

      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        bitField0_ = 0;
        if (authorizationBuilder_ == null) {
          authorization_ = java.util.Collections.emptyList();
        } else {
          authorization_ = null;
          authorizationBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.cosmos.authz.v1beta1.GenesisProto.internal_static_cosmos_authz_v1beta1_GenesisState_descriptor;
      }

      @java.lang.Override
      public com.cosmos.authz.v1beta1.GenesisProto.GenesisState getDefaultInstanceForType() {
        return com.cosmos.authz.v1beta1.GenesisProto.GenesisState.getDefaultInstance();
      }

      @java.lang.Override
      public com.cosmos.authz.v1beta1.GenesisProto.GenesisState build() {
        com.cosmos.authz.v1beta1.GenesisProto.GenesisState result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.cosmos.authz.v1beta1.GenesisProto.GenesisState buildPartial() {
        com.cosmos.authz.v1beta1.GenesisProto.GenesisState result = new com.cosmos.authz.v1beta1.GenesisProto.GenesisState(this);
        buildPartialRepeatedFields(result);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartialRepeatedFields(com.cosmos.authz.v1beta1.GenesisProto.GenesisState result) {
        if (authorizationBuilder_ == null) {
          if (((bitField0_ & 0x00000001) != 0)) {
            authorization_ = java.util.Collections.unmodifiableList(authorization_);
            bitField0_ = (bitField0_ & ~0x00000001);
          }
          result.authorization_ = authorization_;
        } else {
          result.authorization_ = authorizationBuilder_.build();
        }
      }

      private void buildPartial0(com.cosmos.authz.v1beta1.GenesisProto.GenesisState result) {
        int from_bitField0_ = bitField0_;
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
        if (other instanceof com.cosmos.authz.v1beta1.GenesisProto.GenesisState) {
          return mergeFrom((com.cosmos.authz.v1beta1.GenesisProto.GenesisState)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.cosmos.authz.v1beta1.GenesisProto.GenesisState other) {
        if (other == com.cosmos.authz.v1beta1.GenesisProto.GenesisState.getDefaultInstance()) return this;
        if (authorizationBuilder_ == null) {
          if (!other.authorization_.isEmpty()) {
            if (authorization_.isEmpty()) {
              authorization_ = other.authorization_;
              bitField0_ = (bitField0_ & ~0x00000001);
            } else {
              ensureAuthorizationIsMutable();
              authorization_.addAll(other.authorization_);
            }
            onChanged();
          }
        } else {
          if (!other.authorization_.isEmpty()) {
            if (authorizationBuilder_.isEmpty()) {
              authorizationBuilder_.dispose();
              authorizationBuilder_ = null;
              authorization_ = other.authorization_;
              bitField0_ = (bitField0_ & ~0x00000001);
              authorizationBuilder_ = 
                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                   getAuthorizationFieldBuilder() : null;
            } else {
              authorizationBuilder_.addAllMessages(other.authorization_);
            }
          }
        }
        this.mergeUnknownFields(other.getUnknownFields());
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
        if (extensionRegistry == null) {
          throw new java.lang.NullPointerException();
        }
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              case 10: {
                com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization m =
                    input.readMessage(
                        com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization.parser(),
                        extensionRegistry);
                if (authorizationBuilder_ == null) {
                  ensureAuthorizationIsMutable();
                  authorization_.add(m);
                } else {
                  authorizationBuilder_.addMessage(m);
                }
                break;
              } // case 10
              default: {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
            } // switch (tag)
          } // while (!done)
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.unwrapIOException();
        } finally {
          onChanged();
        } // finally
        return this;
      }
      private int bitField0_;

      private java.util.List<com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization> authorization_ =
        java.util.Collections.emptyList();
      private void ensureAuthorizationIsMutable() {
        if (!((bitField0_ & 0x00000001) != 0)) {
          authorization_ = new java.util.ArrayList<com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization>(authorization_);
          bitField0_ |= 0x00000001;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilderV3<
          com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization, com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization.Builder, com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorizationOrBuilder> authorizationBuilder_;

      /**
       * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public java.util.List<com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization> getAuthorizationList() {
        if (authorizationBuilder_ == null) {
          return java.util.Collections.unmodifiableList(authorization_);
        } else {
          return authorizationBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public int getAuthorizationCount() {
        if (authorizationBuilder_ == null) {
          return authorization_.size();
        } else {
          return authorizationBuilder_.getCount();
        }
      }
      /**
       * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization getAuthorization(int index) {
        if (authorizationBuilder_ == null) {
          return authorization_.get(index);
        } else {
          return authorizationBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public Builder setAuthorization(
          int index, com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization value) {
        if (authorizationBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureAuthorizationIsMutable();
          authorization_.set(index, value);
          onChanged();
        } else {
          authorizationBuilder_.setMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public Builder setAuthorization(
          int index, com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization.Builder builderForValue) {
        if (authorizationBuilder_ == null) {
          ensureAuthorizationIsMutable();
          authorization_.set(index, builderForValue.build());
          onChanged();
        } else {
          authorizationBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public Builder addAuthorization(com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization value) {
        if (authorizationBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureAuthorizationIsMutable();
          authorization_.add(value);
          onChanged();
        } else {
          authorizationBuilder_.addMessage(value);
        }
        return this;
      }
      /**
       * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public Builder addAuthorization(
          int index, com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization value) {
        if (authorizationBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureAuthorizationIsMutable();
          authorization_.add(index, value);
          onChanged();
        } else {
          authorizationBuilder_.addMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public Builder addAuthorization(
          com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization.Builder builderForValue) {
        if (authorizationBuilder_ == null) {
          ensureAuthorizationIsMutable();
          authorization_.add(builderForValue.build());
          onChanged();
        } else {
          authorizationBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public Builder addAuthorization(
          int index, com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization.Builder builderForValue) {
        if (authorizationBuilder_ == null) {
          ensureAuthorizationIsMutable();
          authorization_.add(index, builderForValue.build());
          onChanged();
        } else {
          authorizationBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public Builder addAllAuthorization(
          java.lang.Iterable<? extends com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization> values) {
        if (authorizationBuilder_ == null) {
          ensureAuthorizationIsMutable();
          com.google.protobuf.AbstractMessageLite.Builder.addAll(
              values, authorization_);
          onChanged();
        } else {
          authorizationBuilder_.addAllMessages(values);
        }
        return this;
      }
      /**
       * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public Builder clearAuthorization() {
        if (authorizationBuilder_ == null) {
          authorization_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
          onChanged();
        } else {
          authorizationBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public Builder removeAuthorization(int index) {
        if (authorizationBuilder_ == null) {
          ensureAuthorizationIsMutable();
          authorization_.remove(index);
          onChanged();
        } else {
          authorizationBuilder_.remove(index);
        }
        return this;
      }
      /**
       * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization.Builder getAuthorizationBuilder(
          int index) {
        return getAuthorizationFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorizationOrBuilder getAuthorizationOrBuilder(
          int index) {
        if (authorizationBuilder_ == null) {
          return authorization_.get(index);  } else {
          return authorizationBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public java.util.List<? extends com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorizationOrBuilder> 
           getAuthorizationOrBuilderList() {
        if (authorizationBuilder_ != null) {
          return authorizationBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(authorization_);
        }
      }
      /**
       * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization.Builder addAuthorizationBuilder() {
        return getAuthorizationFieldBuilder().addBuilder(
            com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization.getDefaultInstance());
      }
      /**
       * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization.Builder addAuthorizationBuilder(
          int index) {
        return getAuthorizationFieldBuilder().addBuilder(
            index, com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization.getDefaultInstance());
      }
      /**
       * <code>repeated .cosmos.authz.v1beta1.GrantAuthorization authorization = 1 [json_name = "authorization", (.gogoproto.nullable) = false, (.amino.dont_omitempty) = true];</code>
       */
      public java.util.List<com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization.Builder> 
           getAuthorizationBuilderList() {
        return getAuthorizationFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilderV3<
          com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization, com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization.Builder, com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorizationOrBuilder> 
          getAuthorizationFieldBuilder() {
        if (authorizationBuilder_ == null) {
          authorizationBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
              com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization, com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorization.Builder, com.cosmos.authz.v1beta1.AuthzProto.GrantAuthorizationOrBuilder>(
                  authorization_,
                  ((bitField0_ & 0x00000001) != 0),
                  getParentForChildren(),
                  isClean());
          authorization_ = null;
        }
        return authorizationBuilder_;
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


      // @@protoc_insertion_point(builder_scope:cosmos.authz.v1beta1.GenesisState)
    }

    // @@protoc_insertion_point(class_scope:cosmos.authz.v1beta1.GenesisState)
    private static final com.cosmos.authz.v1beta1.GenesisProto.GenesisState DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.cosmos.authz.v1beta1.GenesisProto.GenesisState();
    }

    public static com.cosmos.authz.v1beta1.GenesisProto.GenesisState getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<GenesisState>
        PARSER = new com.google.protobuf.AbstractParser<GenesisState>() {
      @java.lang.Override
      public GenesisState parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        Builder builder = newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (com.google.protobuf.UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (java.io.IOException e) {
          throw new com.google.protobuf.InvalidProtocolBufferException(e)
              .setUnfinishedMessage(builder.buildPartial());
        }
        return builder.buildPartial();
      }
    };

    public static com.google.protobuf.Parser<GenesisState> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<GenesisState> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.cosmos.authz.v1beta1.GenesisProto.GenesisState getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_cosmos_authz_v1beta1_GenesisState_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_cosmos_authz_v1beta1_GenesisState_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\"cosmos/authz/v1beta1/genesis.proto\022\024co" +
      "smos.authz.v1beta1\032\024gogoproto/gogo.proto" +
      "\032 cosmos/authz/v1beta1/authz.proto\032\021amin" +
      "o/amino.proto\"i\n\014GenesisState\022Y\n\rauthori" +
      "zation\030\001 \003(\0132(.cosmos.authz.v1beta1.Gran" +
      "tAuthorizationB\t\310\336\037\000\250\347\260*\001R\rauthorization" +
      "B\256\001\n\030com.cosmos.authz.v1beta1B\014GenesisPr" +
      "otoZ\024cosmossdk.io/x/authz\242\002\003CAX\252\002\024Cosmos" +
      ".Authz.V1beta1\312\002\024Cosmos\\Authz\\V1beta1\342\002 " +
      "Cosmos\\Authz\\V1beta1\\GPBMetadata\352\002\026Cosmo" +
      "s::Authz::V1beta1b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.gogoproto.GogoProto.getDescriptor(),
          com.cosmos.authz.v1beta1.AuthzProto.getDescriptor(),
          com.amino.AminoProto.getDescriptor(),
        });
    internal_static_cosmos_authz_v1beta1_GenesisState_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_cosmos_authz_v1beta1_GenesisState_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_cosmos_authz_v1beta1_GenesisState_descriptor,
        new java.lang.String[] { "Authorization", });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.amino.AminoProto.dontOmitempty);
    registry.add(com.gogoproto.GogoProto.nullable);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.gogoproto.GogoProto.getDescriptor();
    com.cosmos.authz.v1beta1.AuthzProto.getDescriptor();
    com.amino.AminoProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}