// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: provenance/msgfees/v1/query.proto

package io.provenance.msgfees.v1;

public final class QueryOuterClass {
  private QueryOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_provenance_msgfees_v1_QueryParamsRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_provenance_msgfees_v1_QueryParamsRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_provenance_msgfees_v1_QueryParamsResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_provenance_msgfees_v1_QueryParamsResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_provenance_msgfees_v1_QueryAllMsgFeesRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_provenance_msgfees_v1_QueryAllMsgFeesRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_provenance_msgfees_v1_QueryAllMsgFeesResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_provenance_msgfees_v1_QueryAllMsgFeesResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_provenance_msgfees_v1_CalculateTxFeesRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_provenance_msgfees_v1_CalculateTxFeesRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_provenance_msgfees_v1_CalculateTxFeesResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_provenance_msgfees_v1_CalculateTxFeesResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n!provenance/msgfees/v1/query.proto\022\025pro" +
      "venance.msgfees.v1\032\024gogoproto/gogo.proto" +
      "\032\034google/api/annotations.proto\032#provenan" +
      "ce/msgfees/v1/msgfees.proto\032\036cosmos/base" +
      "/v1beta1/coin.proto\032*cosmos/base/query/v" +
      "1beta1/pagination.proto\"\024\n\022QueryParamsRe" +
      "quest\"J\n\023QueryParamsResponse\0223\n\006params\030\001" +
      " \001(\0132\035.provenance.msgfees.v1.ParamsB\004\310\336\037" +
      "\000\"T\n\026QueryAllMsgFeesRequest\022:\n\npaginatio" +
      "n\030\002 \001(\0132&.cosmos.base.query.v1beta1.Page" +
      "Request\"\207\001\n\027QueryAllMsgFeesResponse\022/\n\010m" +
      "sg_fees\030\001 \003(\0132\035.provenance.msgfees.v1.Ms" +
      "gFee\022;\n\npagination\030\002 \001(\0132\'.cosmos.base.q" +
      "uery.v1beta1.PageResponse\"^\n\026CalculateTx" +
      "FeesRequest\022\020\n\010tx_bytes\030\001 \001(\014\022\032\n\022default" +
      "_base_denom\030\002 \001(\t\022\026\n\016gas_adjustment\030\003 \001(" +
      "\002\"\221\002\n\027CalculateTxFeesResponse\022d\n\017additio" +
      "nal_fees\030\001 \003(\0132\031.cosmos.base.v1beta1.Coi" +
      "nB0\310\336\037\000\252\337\037(github.com/cosmos/cosmos-sdk/" +
      "types.Coins\022_\n\ntotal_fees\030\002 \003(\0132\031.cosmos" +
      ".base.v1beta1.CoinB0\310\336\037\000\252\337\037(github.com/c" +
      "osmos/cosmos-sdk/types.Coins\022/\n\restimate" +
      "d_gas\030\003 \001(\004B\030\362\336\037\024yaml:\"estimated_gas\"2\320\003" +
      "\n\005Query\022\206\001\n\006Params\022).provenance.msgfees." +
      "v1.QueryParamsRequest\032*.provenance.msgfe" +
      "es.v1.QueryParamsResponse\"%\202\323\344\223\002\037\022\035/prov" +
      "enance/msgfees/v1/params\022\224\001\n\017QueryAllMsg" +
      "Fees\022-.provenance.msgfees.v1.QueryAllMsg" +
      "FeesRequest\032..provenance.msgfees.v1.Quer" +
      "yAllMsgFeesResponse\"\"\202\323\344\223\002\034\022\032/provenance" +
      "/msgfees/v1/all\022\246\001\n\017CalculateTxFees\022-.pr" +
      "ovenance.msgfees.v1.CalculateTxFeesReque" +
      "st\032..provenance.msgfees.v1.CalculateTxFe" +
      "esResponse\"4\202\323\344\223\002.\")/provenance/tx/v1/ca" +
      "lculate_msg_based_fee:\001*BQ\n\030io.provenanc" +
      "e.msgfees.v1P\001Z3github.com/provenance-io" +
      "/provenance/x/msgfees/typesb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf2.GoGoProtos.getDescriptor(),
          com.google.api.AnnotationsProto.getDescriptor(),
          io.provenance.msgfees.v1.Msgfees.getDescriptor(),
          cosmos.base.v1beta1.CoinOuterClass.getDescriptor(),
          cosmos.base.query.v1beta1.Pagination.getDescriptor(),
        });
    internal_static_provenance_msgfees_v1_QueryParamsRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_provenance_msgfees_v1_QueryParamsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_provenance_msgfees_v1_QueryParamsRequest_descriptor,
        new java.lang.String[] { });
    internal_static_provenance_msgfees_v1_QueryParamsResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_provenance_msgfees_v1_QueryParamsResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_provenance_msgfees_v1_QueryParamsResponse_descriptor,
        new java.lang.String[] { "Params", });
    internal_static_provenance_msgfees_v1_QueryAllMsgFeesRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_provenance_msgfees_v1_QueryAllMsgFeesRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_provenance_msgfees_v1_QueryAllMsgFeesRequest_descriptor,
        new java.lang.String[] { "Pagination", });
    internal_static_provenance_msgfees_v1_QueryAllMsgFeesResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_provenance_msgfees_v1_QueryAllMsgFeesResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_provenance_msgfees_v1_QueryAllMsgFeesResponse_descriptor,
        new java.lang.String[] { "MsgFees", "Pagination", });
    internal_static_provenance_msgfees_v1_CalculateTxFeesRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_provenance_msgfees_v1_CalculateTxFeesRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_provenance_msgfees_v1_CalculateTxFeesRequest_descriptor,
        new java.lang.String[] { "TxBytes", "DefaultBaseDenom", "GasAdjustment", });
    internal_static_provenance_msgfees_v1_CalculateTxFeesResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_provenance_msgfees_v1_CalculateTxFeesResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_provenance_msgfees_v1_CalculateTxFeesResponse_descriptor,
        new java.lang.String[] { "AdditionalFees", "TotalFees", "EstimatedGas", });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.google.protobuf2.GoGoProtos.castrepeated);
    registry.add(com.google.protobuf2.GoGoProtos.moretags);
    registry.add(com.google.protobuf2.GoGoProtos.nullable);
    registry.add(com.google.api.AnnotationsProto.http);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.google.protobuf2.GoGoProtos.getDescriptor();
    com.google.api.AnnotationsProto.getDescriptor();
    io.provenance.msgfees.v1.Msgfees.getDescriptor();
    cosmos.base.v1beta1.CoinOuterClass.getDescriptor();
    cosmos.base.query.v1beta1.Pagination.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
