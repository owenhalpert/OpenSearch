// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: extensions/HandleTransportRequestProto.proto

package org.opensearch.extensions.proto;

@javax.annotation.Generated(value="protoc", comments="annotations:ExtensionTransportMessageProto.java.pb.meta")
public final class ExtensionTransportMessageProto {
  private ExtensionTransportMessageProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ExtensionTransportMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:org.opensearch.extensions.proto.ExtensionTransportMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string action = 1;</code>
     * @return The action.
     */
    java.lang.String getAction();
    /**
     * <code>string action = 1;</code>
     * @return The bytes for action.
     */
    com.google.protobuf.ByteString
        getActionBytes();

    /**
     * <code>bytes requestBytes = 2;</code>
     * @return The requestBytes.
     */
    com.google.protobuf.ByteString getRequestBytes();
  }
  /**
   * Protobuf type {@code org.opensearch.extensions.proto.ExtensionTransportMessage}
   */
  public static final class ExtensionTransportMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:org.opensearch.extensions.proto.ExtensionTransportMessage)
      ExtensionTransportMessageOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ExtensionTransportMessage.newBuilder() to construct.
    private ExtensionTransportMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ExtensionTransportMessage() {
      action_ = "";
      requestBytes_ = com.google.protobuf.ByteString.EMPTY;
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new ExtensionTransportMessage();
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.opensearch.extensions.proto.ExtensionTransportMessageProto.internal_static_org_opensearch_extensions_proto_ExtensionTransportMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.opensearch.extensions.proto.ExtensionTransportMessageProto.internal_static_org_opensearch_extensions_proto_ExtensionTransportMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage.class, org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage.Builder.class);
    }

    public static final int ACTION_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object action_ = "";
    /**
     * <code>string action = 1;</code>
     * @return The action.
     */
    @java.lang.Override
    public java.lang.String getAction() {
      java.lang.Object ref = action_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        action_ = s;
        return s;
      }
    }
    /**
     * <code>string action = 1;</code>
     * @return The bytes for action.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getActionBytes() {
      java.lang.Object ref = action_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        action_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int REQUESTBYTES_FIELD_NUMBER = 2;
    private com.google.protobuf.ByteString requestBytes_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes requestBytes = 2;</code>
     * @return The requestBytes.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getRequestBytes() {
      return requestBytes_;
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
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(action_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, action_);
      }
      if (!requestBytes_.isEmpty()) {
        output.writeBytes(2, requestBytes_);
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(action_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, action_);
      }
      if (!requestBytes_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, requestBytes_);
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
      if (!(obj instanceof org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage)) {
        return super.equals(obj);
      }
      org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage other = (org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage) obj;

      if (!getAction()
          .equals(other.getAction())) return false;
      if (!getRequestBytes()
          .equals(other.getRequestBytes())) return false;
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
      hash = (37 * hash) + ACTION_FIELD_NUMBER;
      hash = (53 * hash) + getAction().hashCode();
      hash = (37 * hash) + REQUESTBYTES_FIELD_NUMBER;
      hash = (53 * hash) + getRequestBytes().hashCode();
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage parseFrom(
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
    public static Builder newBuilder(org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage prototype) {
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
     * Protobuf type {@code org.opensearch.extensions.proto.ExtensionTransportMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:org.opensearch.extensions.proto.ExtensionTransportMessage)
        org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return org.opensearch.extensions.proto.ExtensionTransportMessageProto.internal_static_org_opensearch_extensions_proto_ExtensionTransportMessage_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return org.opensearch.extensions.proto.ExtensionTransportMessageProto.internal_static_org_opensearch_extensions_proto_ExtensionTransportMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage.class, org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage.Builder.class);
      }

      // Construct using org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage.newBuilder()
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
        action_ = "";
        requestBytes_ = com.google.protobuf.ByteString.EMPTY;
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return org.opensearch.extensions.proto.ExtensionTransportMessageProto.internal_static_org_opensearch_extensions_proto_ExtensionTransportMessage_descriptor;
      }

      @java.lang.Override
      public org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage getDefaultInstanceForType() {
        return org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage.getDefaultInstance();
      }

      @java.lang.Override
      public org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage build() {
        org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage buildPartial() {
        org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage result = new org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.action_ = action_;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.requestBytes_ = requestBytes_;
        }
      }

      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage) {
          return mergeFrom((org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage other) {
        if (other == org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage.getDefaultInstance()) return this;
        if (!other.getAction().isEmpty()) {
          action_ = other.action_;
          bitField0_ |= 0x00000001;
          onChanged();
        }
        if (other.getRequestBytes() != com.google.protobuf.ByteString.EMPTY) {
          setRequestBytes(other.getRequestBytes());
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
                action_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000001;
                break;
              } // case 10
              case 18: {
                requestBytes_ = input.readBytes();
                bitField0_ |= 0x00000002;
                break;
              } // case 18
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

      private java.lang.Object action_ = "";
      /**
       * <code>string action = 1;</code>
       * @return The action.
       */
      public java.lang.String getAction() {
        java.lang.Object ref = action_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          action_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string action = 1;</code>
       * @return The bytes for action.
       */
      public com.google.protobuf.ByteString
          getActionBytes() {
        java.lang.Object ref = action_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          action_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string action = 1;</code>
       * @param value The action to set.
       * @return This builder for chaining.
       */
      public Builder setAction(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        action_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <code>string action = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearAction() {
        action_ = getDefaultInstance().getAction();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }
      /**
       * <code>string action = 1;</code>
       * @param value The bytes for action to set.
       * @return This builder for chaining.
       */
      public Builder setActionBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        action_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString requestBytes_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>bytes requestBytes = 2;</code>
       * @return The requestBytes.
       */
      @java.lang.Override
      public com.google.protobuf.ByteString getRequestBytes() {
        return requestBytes_;
      }
      /**
       * <code>bytes requestBytes = 2;</code>
       * @param value The requestBytes to set.
       * @return This builder for chaining.
       */
      public Builder setRequestBytes(com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        requestBytes_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <code>bytes requestBytes = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearRequestBytes() {
        bitField0_ = (bitField0_ & ~0x00000002);
        requestBytes_ = getDefaultInstance().getRequestBytes();
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


      // @@protoc_insertion_point(builder_scope:org.opensearch.extensions.proto.ExtensionTransportMessage)
    }

    // @@protoc_insertion_point(class_scope:org.opensearch.extensions.proto.ExtensionTransportMessage)
    private static final org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage();
    }

    public static org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ExtensionTransportMessage>
        PARSER = new com.google.protobuf.AbstractParser<ExtensionTransportMessage>() {
      @java.lang.Override
      public ExtensionTransportMessage parsePartialFrom(
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

    public static com.google.protobuf.Parser<ExtensionTransportMessage> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ExtensionTransportMessage> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public org.opensearch.extensions.proto.ExtensionTransportMessageProto.ExtensionTransportMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_opensearch_extensions_proto_ExtensionTransportMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_org_opensearch_extensions_proto_ExtensionTransportMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n,extensions/HandleTransportRequestProto" +
      ".proto\022\037org.opensearch.extensions.proto\"" +
      "A\n\031ExtensionTransportMessage\022\016\n\006action\030\001" +
      " \001(\t\022\024\n\014requestBytes\030\002 \001(\014B B\036ExtensionT" +
      "ransportMessageProtob\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_org_opensearch_extensions_proto_ExtensionTransportMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_org_opensearch_extensions_proto_ExtensionTransportMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_org_opensearch_extensions_proto_ExtensionTransportMessage_descriptor,
        new java.lang.String[] { "Action", "RequestBytes", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
