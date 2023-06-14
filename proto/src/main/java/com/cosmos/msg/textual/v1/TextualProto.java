// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: cosmos/msg/textual/v1/textual.proto

package com.cosmos.msg.textual.v1;

public final class TextualProto {
  private TextualProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
    registry.add(com.cosmos.msg.textual.v1.TextualProto.expertCustomRenderer);
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public static final int EXPERT_CUSTOM_RENDERER_FIELD_NUMBER = 11110009;
  /**
   * <pre>
   * expert_custom_renderer is an informative identifier to reference the
   * algorithm used to generate the custom textual representation of the
   * protobuf message where this annotation is applied. We recommend to use a
   * short, versioned name as this identifier, e.g. "replace_with_username_v1".
   * We also recommand providing a human-readable description as protobuf
   * comments on this annotation, for example a short specification or a link
   * to the relevant documentation.
   *
   * Also see the section on Custom Message Renderers in ADR-050.
   * </pre>
   *
   * <code>extend .google.protobuf.MessageOptions { ... }</code>
   */
  public static final
    com.google.protobuf.GeneratedMessage.GeneratedExtension<
      com.google.protobuf.DescriptorProtos.MessageOptions,
      java.lang.String> expertCustomRenderer = com.google.protobuf.GeneratedMessage
          .newFileScopedGeneratedExtension(
        java.lang.String.class,
        null);

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n#cosmos/msg/textual/v1/textual.proto\022\025c" +
      "osmos.msg.textual.v1\032 google/protobuf/de" +
      "scriptor.proto:X\n\026expert_custom_renderer" +
      "\022\037.google.protobuf.MessageOptions\030\371\214\246\005 \001" +
      "(\tR\024expertCustomRendererB\236\001\n\031com.cosmos." +
      "msg.textual.v1B\014TextualProto\242\002\003CMT\252\002\025Cos" +
      "mos.Msg.Textual.V1\312\002\025Cosmos\\Msg\\Textual\\" +
      "V1\342\002!Cosmos\\Msg\\Textual\\V1\\GPBMetadata\352\002" +
      "\030Cosmos::Msg::Textual::V1b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.DescriptorProtos.getDescriptor(),
        });
    expertCustomRenderer.internalInit(descriptor.getExtensions().get(0));
    com.google.protobuf.DescriptorProtos.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
