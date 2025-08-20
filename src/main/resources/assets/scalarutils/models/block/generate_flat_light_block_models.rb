require 'json'

colors = %w[
  white light_gray gray black brown red orange yellow lime green
  cyan light_blue blue purple magenta pink
]

colors.each do |color|
  # Normal variant (unlit)
  normal_model = {
    parent: "minecraft:block/cube_all",
    textures: {
      all: "scalarutils:block/#{color}_flat_light"
    }
  }
  File.write("#{color}_flat_light.json", JSON.pretty_generate(normal_model))

  # Normal variant (lit)
  lit_model = {
    parent: "minecraft:block/cube_all",
    textures: {
      all: "scalarutils:block/#{color}_flat_light_on"
    }
  }
  File.write("#{color}_flat_light_on.json", JSON.pretty_generate(lit_model))

  # Inverted variant (unlit)
  inverted_model = {
    parent: "minecraft:block/cube_all",
    textures: {
      all: "scalarutils:block/#{color}_flat_light_on"
    }
  }
  File.write("inverted_#{color}_flat_light.json", JSON.pretty_generate(inverted_model))

  # Inverted variant (lit)
  inverted_lit_model = {
    parent: "minecraft:block/cube_all",
    textures: {
      all: "scalarutils:block/#{color}_flat_light"
    }
  }
  File.write("inverted_#{color}_flat_light_on.json", JSON.pretty_generate(inverted_lit_model))
end