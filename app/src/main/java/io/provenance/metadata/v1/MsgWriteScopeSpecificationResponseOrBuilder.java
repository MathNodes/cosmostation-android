// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: provenance/metadata/v1/tx.proto

package io.provenance.metadata.v1;

public interface MsgWriteScopeSpecificationResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:provenance.metadata.v1.MsgWriteScopeSpecificationResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * scope_spec_id_info contains information about the id/address of the scope specification that was added or updated.
   * </pre>
   *
   * <code>.provenance.metadata.v1.ScopeSpecIdInfo scope_spec_id_info = 1 [(.gogoproto.moretags) = "yaml:&#92;"scope_spec_id_info&#92;""];</code>
   * @return Whether the scopeSpecIdInfo field is set.
   */
  boolean hasScopeSpecIdInfo();
  /**
   * <pre>
   * scope_spec_id_info contains information about the id/address of the scope specification that was added or updated.
   * </pre>
   *
   * <code>.provenance.metadata.v1.ScopeSpecIdInfo scope_spec_id_info = 1 [(.gogoproto.moretags) = "yaml:&#92;"scope_spec_id_info&#92;""];</code>
   * @return The scopeSpecIdInfo.
   */
  io.provenance.metadata.v1.ScopeSpecIdInfo getScopeSpecIdInfo();
  /**
   * <pre>
   * scope_spec_id_info contains information about the id/address of the scope specification that was added or updated.
   * </pre>
   *
   * <code>.provenance.metadata.v1.ScopeSpecIdInfo scope_spec_id_info = 1 [(.gogoproto.moretags) = "yaml:&#92;"scope_spec_id_info&#92;""];</code>
   */
  io.provenance.metadata.v1.ScopeSpecIdInfoOrBuilder getScopeSpecIdInfoOrBuilder();
}