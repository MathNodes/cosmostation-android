// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: provenance/metadata/v1/p8e/p8e.proto

package io.provenance.metadata.v1.p8e;

public interface ProposedFactOrBuilder extends
    // @@protoc_insertion_point(interface_extends:provenance.metadata.v1.p8e.ProposedFact)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string name = 1;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>string hash = 2;</code>
   * @return The hash.
   */
  java.lang.String getHash();
  /**
   * <code>string hash = 2;</code>
   * @return The bytes for hash.
   */
  com.google.protobuf.ByteString
      getHashBytes();

  /**
   * <code>string classname = 3;</code>
   * @return The classname.
   */
  java.lang.String getClassname();
  /**
   * <code>string classname = 3;</code>
   * @return The bytes for classname.
   */
  com.google.protobuf.ByteString
      getClassnameBytes();

  /**
   * <code>.provenance.metadata.v1.p8e.ProvenanceReference ancestor = 4;</code>
   * @return Whether the ancestor field is set.
   */
  boolean hasAncestor();
  /**
   * <code>.provenance.metadata.v1.p8e.ProvenanceReference ancestor = 4;</code>
   * @return The ancestor.
   */
  io.provenance.metadata.v1.p8e.ProvenanceReference getAncestor();
  /**
   * <code>.provenance.metadata.v1.p8e.ProvenanceReference ancestor = 4;</code>
   */
  io.provenance.metadata.v1.p8e.ProvenanceReferenceOrBuilder getAncestorOrBuilder();
}