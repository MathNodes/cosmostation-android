// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: provenance/metadata/v1/query.proto

package io.provenance.metadata.v1;

public interface RecordSpecificationRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:provenance.metadata.v1.RecordSpecificationRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * specification_id can either be a uuid, e.g. def6bc0a-c9dd-4874-948f-5206e6060a84 or a bech32 contract specification
   * address, e.g. contractspec1q000d0q2e8w5say53afqdesxp2zqzkr4fn.
   * It can also be a record specification address, e.g.
   * recspec1qh00d0q2e8w5say53afqdesxp2zw42dq2jdvmdazuwzcaddhh8gmuqhez44.
   * </pre>
   *
   * <code>string specification_id = 1 [(.gogoproto.moretags) = "yaml:&#92;"specification_id&#92;""];</code>
   * @return The specificationId.
   */
  java.lang.String getSpecificationId();
  /**
   * <pre>
   * specification_id can either be a uuid, e.g. def6bc0a-c9dd-4874-948f-5206e6060a84 or a bech32 contract specification
   * address, e.g. contractspec1q000d0q2e8w5say53afqdesxp2zqzkr4fn.
   * It can also be a record specification address, e.g.
   * recspec1qh00d0q2e8w5say53afqdesxp2zw42dq2jdvmdazuwzcaddhh8gmuqhez44.
   * </pre>
   *
   * <code>string specification_id = 1 [(.gogoproto.moretags) = "yaml:&#92;"specification_id&#92;""];</code>
   * @return The bytes for specificationId.
   */
  com.google.protobuf.ByteString
      getSpecificationIdBytes();

  /**
   * <pre>
   * name is the name of the record to look up.
   * It is required if the specification_id is a uuid or contract specification address.
   * It is ignored if the specification_id is a record specification address.
   * </pre>
   *
   * <code>string name = 2;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <pre>
   * name is the name of the record to look up.
   * It is required if the specification_id is a uuid or contract specification address.
   * It is ignored if the specification_id is a record specification address.
   * </pre>
   *
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();
}
